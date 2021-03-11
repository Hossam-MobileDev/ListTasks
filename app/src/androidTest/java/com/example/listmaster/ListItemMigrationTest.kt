

package com.example.listmaster

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory
import android.arch.persistence.room.Room
import android.arch.persistence.room.testing.MigrationTestHelper
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.example.listmaster.listitem.ListItem
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListItemMigrationTest {

  private val TEST_DB_NAME = "migration_test"

  private lateinit var database: SupportSQLiteDatabase

  //1
  @Rule
  @JvmField
  val migrationTestHelperRule = MigrationTestHelper(
      InstrumentationRegistry.getInstrumentation(),
      "com.raywenderlich.listmaster.AppDatabase",
      FrameworkSQLiteOpenHelperFactory())

  //2
  @Before
  fun setup() {
    database = migrationTestHelperRule.createDatabase(TEST_DB_NAME, 1)
    database.execSQL("INSERT INTO list_categories (id, category_name) VALUES" +
        " (1, 'Purr Programming Supplies'), (2, 'Canine Coding Supplies')")
  }

  @Test
  fun migrating_from_1_to_3_retains_version_1_data() {
    val listCategories = getMigratedRoomDatabase().listCategoryDao().getAll().blockingObserve()
    assertEquals(2, listCategories!!.size)
    assertEquals("Purr Programming Supplies", listCategories.first().categoryName)
    assertEquals(1, listCategories.first().id)
    assertEquals("Canine Coding Supplies", listCategories.last().categoryName)
    assertEquals(2, listCategories.last().id)
  }

  @Test
  fun inserting_a_record_into_list_items_after_migrating_from_1_to_3_succeeds() {
    val listCategories = getMigratedRoomDatabase().listCategoryDao().getAll().blockingObserve()
    // insert a record in the new table
    val listItemDao = getMigratedRoomDatabase().listItemDao()
    val purrProgrammingListItem = ListItem("desk cushion", 1,
        listCategories!!.first().id)
    listItemDao.insertAll(purrProgrammingListItem)

    // validate that a record can be added to the new table
    val purrProgrammingList = listItemDao.getAll().blockingObserve()
    assertEquals(1, purrProgrammingList!!.size)
    val firstPurrProgrammingItem = purrProgrammingList.first()
    assertEquals("desk cushion", firstPurrProgrammingItem.itemDescription)
    assertEquals(1, firstPurrProgrammingItem.itemPriority)
    assertEquals(listCategories.first().id, firstPurrProgrammingItem.listCategoryId)
    assertEquals(1, firstPurrProgrammingItem.id)
  }

  //3
  @After
  fun teardown() {
    database.execSQL("DROP TABLE IF EXISTS list_categories")
    database.execSQL("DROP TABLE IF EXISTS list_items")
    database.close()
  }

  private fun getMigratedRoomDatabase(): AppDatabase {
    //1
    val appDatabase = Room.databaseBuilder(
        InstrumentationRegistry.getTargetContext(),
        AppDatabase::class.java, TEST_DB_NAME)
        //2
        .addMigrations(AppDatabase.MIGRATION_1_TO_2)
        .addMigrations(AppDatabase.MIGRATION_2_TO_3)
        //3
        .build()
    //4
    migrationTestHelperRule.closeWhenFinished(appDatabase)
    return appDatabase
  }
}
