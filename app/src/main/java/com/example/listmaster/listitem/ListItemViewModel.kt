

package com.example.listmaster.listitem

data class ListItemViewModel(var listItem: ListItem) {

  fun setPriority(priority: String) {
    if (priority.isNotEmpty())
      listItem.itemPriority = Integer.valueOf(priority)
  }

  fun getPriority(): String {
    return listItem.itemPriority.toString()
  }
}