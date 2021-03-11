

package com.example.listmaster

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.support.annotation.VisibleForTesting
import com.example.listmaster.listcategory.ListCategory
import com.example.listmaster.listcategory.ListCategoryDao
import com.example.listmaster.listitem.ListItem
import com.example.listmaster.listitem.ListItemDao
import com.example.listmaster.migrations.Migration1To2
import com.example.listmaster.migrations.Migration2To3

//1
@Database(entities = [ListCategory::class, ListItem::class], version = 3)
abstract class AppDatabase : RoomDatabase() {

  abstract fun listCategoryDao(): ListCategoryDao

  abstract fun listItemDao(): ListItemDao

  companion object {

    @VisibleForTesting
    val MIGRATION_1_TO_2 = Migration1To2()
    //2
    @VisibleForTesting
    val MIGRATION_2_TO_3 = Migration2To3()
  }
}
