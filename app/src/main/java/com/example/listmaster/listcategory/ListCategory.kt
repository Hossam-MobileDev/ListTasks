

package com.example.listmaster.listcategory

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "list_categories")
data class ListCategory(@ColumnInfo(name = "category_name") var categoryName: String,
                        @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long = 0)
