

package com.example.listmaster.listcategory

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.listmaster.databinding.HolderListCategoryItemBinding

data class ListCategoryAdapter(
    var categoryList: List<ListCategory>,
    private val listCategoriesActivity: ListCategoriesActivity) :
    RecyclerView.Adapter<ListCategoryViewHolder>() {

  override fun getItemCount(): Int {
    return categoryList.size
  }

  override fun onBindViewHolder(holder: ListCategoryViewHolder, position: Int) {
    holder.setListCategoryItem(categoryList[position])
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCategoryViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val holderListCategoryItemBinding = HolderListCategoryItemBinding.inflate(layoutInflater, parent, false)
    return ListCategoryViewHolder(holderListCategoryItemBinding, listCategoriesActivity)
  }
}
