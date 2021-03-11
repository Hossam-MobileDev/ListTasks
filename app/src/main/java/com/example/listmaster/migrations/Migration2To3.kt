

package com.example.listmaster.migrations

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration
import android.support.annotation.VisibleForTesting

@VisibleForTesting
class Migration2To3 : Migration(2, 3) {

  override fun migrate(database: SupportSQLiteDatabase) {
    database.execSQL(
        "CREATE INDEX 'index_list_category_id' ON list_items('list_category_id')")
  }
}
