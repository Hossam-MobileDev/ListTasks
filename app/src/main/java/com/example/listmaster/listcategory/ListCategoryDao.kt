

package com.example.listmaster.listcategory

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ListCategoryDao {

  @Query("SELECT * FROM list_categories")
  fun getAll(): LiveData<List<ListCategory>>

  @Insert
  fun insertAll(vararg listCategories: ListCategory)
}
