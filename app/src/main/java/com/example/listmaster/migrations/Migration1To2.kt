

package com.example.listmaster.migrations

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration
import android.support.annotation.VisibleForTesting

@VisibleForTesting
class Migration1To2 : Migration(1, 2) {

  override fun migrate(database: SupportSQLiteDatabase) {
    database.execSQL("CREATE TABLE IF NOT EXISTS list_items" +
        "('item_description' TEXT NOT NULL, 'item_priority' INTEGER NOT NULL," +
        "'list_category_id' INTEGER NOT NULL, 'id' INTEGER NOT NULL, PRIMARY KEY(id)," +
        "FOREIGN KEY('list_category_id') REFERENCES list_categories('id') ON DELETE CASCADE)")
  }
}
