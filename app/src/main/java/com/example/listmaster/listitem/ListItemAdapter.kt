

package com.example.listmaster.listitem

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.listmaster.databinding.HolderListItemBinding

data class ListItemAdapter(
    var itemList: List<ListItem>,
    private val listItemsActivity: ListItemsActivity) :
    RecyclerView.Adapter<ListItemViewHolder>() {

  override fun getItemCount(): Int {
    return itemList.size
  }

  override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
    holder.setListItem(itemList[position])
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val holderListItemBinding = HolderListItemBinding.inflate(layoutInflater, parent,
        false)
    return ListItemViewHolder(holderListItemBinding, listItemsActivity)
  }
}
