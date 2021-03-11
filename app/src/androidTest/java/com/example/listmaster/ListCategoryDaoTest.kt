

package com.example.listmaster

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.example.listmaster.listcategory.ListCategory
import com.example.listmaster.listcategory.ListCategoryDao
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListCategoryDaoTest {

  @Rule
  @JvmField
  val rule: TestRule = InstantTaskExecutorRule()

  private lateinit var database: AppDatabase
  private lateinit var listCategoryDao: ListCategoryDao

  @Before
  fun setup() {
    val context: Context = InstrumentationRegistry.getTargetContext()
    try {
      database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
          .allowMainThreadQueries().build()
    } catch (e: Exception) {
      Log.i("test", e.message)
    }
    listCategoryDao = database.listCategoryDao()
  }

  @Test
  fun testAddingAndRetrievingData() {
    // 1
    val preInsertRetrievedCategories = listCategoryDao.getAll().blockingObserve()

    // 2
    val listCategory = ListCategory("Cats", 1)
    listCategoryDao.insertAll(listCategory)

    //3
    val postInsertRetrievedCategories = listCategoryDao.getAll().blockingObserve()
    val sizeDifference = postInsertRetrievedCategories!!.size - preInsertRetrievedCategories!!.size
    Assert.assertEquals(1, sizeDifference)
    val retrievedCategory = postInsertRetrievedCategories.last()
    Assert.assertEquals("Cats", retrievedCategory.categoryName)
  }

  @After
  fun tearDown() {
    database.close()
  }
}
