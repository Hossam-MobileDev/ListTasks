

package com.example.listmaster.listcategory

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

class ListCategoriesViewModel(application: Application) : AndroidViewModel(application) {

  private val listCategoryRepository: ListCategoryRepository = ListCategoryRepository()
  val listCategories: LiveData<List<ListCategory>> = listCategoryRepository.listCategories

  fun insertAll(vararg listCategory: ListCategory) {
    listCategoryRepository.insertAll(*listCategory)
  }
}
