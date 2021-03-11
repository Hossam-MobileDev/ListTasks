

package com.example.listmaster.listcategory

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.listmaster.R
import com.example.listmaster.databinding.ActivityListCategoriesBinding
import com.example.listmaster.databinding.ContentListCategoriesBinding
import com.example.listmaster.databinding.DialogAddCategoryBinding

class ListCategoriesActivity : AppCompatActivity() {

  private lateinit var activityListsBinding: ActivityListCategoriesBinding
  private lateinit var listCategoryAdapter: ListCategoryAdapter

  private lateinit var contentListsBinding: ContentListCategoriesBinding
  private lateinit var listCategoriesViewModel: ListCategoriesViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    activityListsBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_categories)

    setupAddButton()

    listCategoriesViewModel = ViewModelProviders.of(this).get(ListCategoriesViewModel::class.java)

    setupRecyclerAdapter()
  }


  private fun setupAddButton() {
    activityListsBinding.fab.setOnClickListener {

      // Set up the dialog
      val alertDialogBuilder = AlertDialog.Builder(this)
      alertDialogBuilder.setTitle("Title")
      val dialogAddCategoryBinding = DialogAddCategoryBinding.inflate(layoutInflater)

      alertDialogBuilder.setView(dialogAddCategoryBinding.root)

      // Set up the view model that the dialog input fields are bound to
      val listCategoryViewModel = ListCategoryViewModel()
      dialogAddCategoryBinding.listCategoryViewModel = listCategoryViewModel

      alertDialogBuilder.setPositiveButton(android.R.string.ok) { dialog: DialogInterface,
                                                                  which: Int ->
        listCategoriesViewModel.insertAll(listCategoryViewModel.listCategory)
      }

      alertDialogBuilder.setNegativeButton(android.R.string.cancel, null)
      alertDialogBuilder.show()
    }
  }

  private fun setupRecyclerAdapter() {
    val recyclerViewLinearLayoutManager = LinearLayoutManager(this)
    contentListsBinding = activityListsBinding.contentLists!!
    contentListsBinding.listCategoryRecyclerView.layoutManager = recyclerViewLinearLayoutManager
    listCategoryAdapter = ListCategoryAdapter(listOf(), this)
    listCategoriesViewModel.listCategories.observe(this, Observer { listCategories: List<ListCategory>? ->
      listCategories?.let {
        listCategoryAdapter.categoryList = it
        listCategoryAdapter.notifyDataSetChanged()
      }
    })
    contentListsBinding.listCategoryRecyclerView.adapter = listCategoryAdapter
  }
}
