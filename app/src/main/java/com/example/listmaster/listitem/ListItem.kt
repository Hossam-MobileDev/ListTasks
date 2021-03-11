

package com.example.listmaster.listitem

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.example.listmaster.listcategory.ListCategory

@Entity(
    tableName = "list_items",
    foreignKeys = [ForeignKey(
        entity = ListCategory::class,
        parentColumns = ["id"],
        childColumns = ["list_category_id"],
        onDelete = CASCADE)],
    indices = [Index(value = "list_category_id", name = "index_list_category_id")])
data class ListItem(@ColumnInfo(name = "item_description") var itemDescription: String,
                    @ColumnInfo(name = "item_priority") var itemPriority: Int,
                    @ColumnInfo(name = "list_category_id") var listCategoryId: Long,
                    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long = 0)
