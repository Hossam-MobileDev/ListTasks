

package com.example.listmaster.listitem

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ListItemDao {

  @Query("SELECT * FROM list_items")
  fun getAll(): LiveData<List<ListItem>>

  @Query("SELECT * FROM list_items WHERE list_category_id = :listCategoryId")
  fun getAllByListCategoryId(listCategoryId: Long): LiveData<List<ListItem>>

  @Insert
  fun insertAll(vararg listItems: ListItem)
}