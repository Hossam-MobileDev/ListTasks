

package com.example.listmaster.listitem

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

//1
class ListItemsViewModel(application: Application) : AndroidViewModel(application) {

  //2
  private val listItemRepository: ListItemRepository = ListItemRepository()

  //3
  fun insertAll(vararg listItems: ListItem) {
    listItemRepository.insertAll(*listItems)
  }

  fun getAllByListCategoryId(listCategoryId: Long): LiveData<List<ListItem>> {
    return listItemRepository.getAllByListCategoryId(listCategoryId)
  }
}

