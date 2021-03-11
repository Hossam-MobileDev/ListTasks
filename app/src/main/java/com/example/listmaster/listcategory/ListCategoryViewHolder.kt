

package com.example.listmaster.listcategory

import android.content.Intent
import android.support.v7.widget.RecyclerView
import com.example.listmaster.databinding.HolderListCategoryItemBinding
import com.example.listmaster.listitem.ListItemsActivity

data class ListCategoryViewHolder(
    private val holderListCategoryBinding: HolderListCategoryItemBinding,
    private val listCategoriesActivity: ListCategoriesActivity) :
    RecyclerView.ViewHolder(holderListCategoryBinding.root) {

  fun setListCategoryItem(listCategory: ListCategory) {
    val listCategoryViewModel = ListCategoryViewModel(listCategory)
    holderListCategoryBinding.listCategoryItem = listCategoryViewModel
    holderListCategoryBinding.executePendingBindings()
    holderListCategoryBinding.categoryName.rootView.setOnClickListener {
      val intent = Intent(listCategoriesActivity, ListItemsActivity::class.java)
      intent.putExtra(ListItemsActivity.LIST_CATEGORY_ID, listCategory.id)
      intent.putExtra(ListItemsActivity.CATEGORY_NAME, listCategory.categoryName)
      listCategoriesActivity.startActivity(intent)
    }
  }
}
