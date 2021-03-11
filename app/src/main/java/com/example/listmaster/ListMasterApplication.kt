

package com.example.listmaster

import android.app.Application
import android.arch.persistence.room.Room

class ListMasterApplication : Application() {

  companion object {
    var database: AppDatabase? = null
  }

  override fun onCreate() {
    super.onCreate()
    ListMasterApplication.database = Room.databaseBuilder(
        this,
        AppDatabase::class.java,
        "list-master-db")
        .addMigrations(AppDatabase.MIGRATION_1_TO_2)
        .addMigrations(AppDatabase.MIGRATION_2_TO_3)
        .build()
  }
}
