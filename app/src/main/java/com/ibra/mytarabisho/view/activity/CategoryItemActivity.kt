package com.ibra.mytarabisho.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.github.clans.fab.FloatingActionButton
import com.ibra.mytarabisho.R
import com.ibra.mytarabisho.model.entity.Category
import com.ibra.mytarabisho.model.entity.CategoryItem
import com.ibra.mytarabisho.model.entity.Detail
import com.ibra.mytarabisho.view.adapter.CategoryItemRecyclerAdapter
import com.ibra.mytarabisho.view.adapter.CategoryRecyclerAdapter
import com.ibra.mytarabisho.view.dialog.CategoryInfoSheetFragment
import com.ibra.mytarabisho.view.dialog.CategoryItemInfoSheetFragment
import com.ibra.mytarabisho.viewmodel.CategoryItemViewModel
import com.ibra.mytarabisho.viewmodel.DetailViewModel
import java.util.ArrayList

class CategoryItemActivity : AppCompatActivity() {

    private lateinit var categoryItemViewModel: CategoryItemViewModel
    private lateinit var fabCategoryItem : FloatingActionButton
    private var categoryId : Int = 0


    private lateinit var recyclerCategoryItem: RecyclerView
    private lateinit var categoryItemRecyclerAdapter: CategoryItemRecyclerAdapter
    private var categoryItems: List<CategoryItem> = ArrayList<CategoryItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_item)

        categoryId = intent.getIntExtra("categoryId",-1)

        fabCategoryItem=findViewById(R.id.fabCategoryItem)

        categoryItemViewModel = ViewModelProvider(this).get(CategoryItemViewModel::class.java)

        recyclerCategoryItem = findViewById(R.id.recyclerCategoryItem)
        categoryItemRecyclerAdapter = CategoryItemRecyclerAdapter(this,categoryItems)
        recyclerCategoryItem.adapter = categoryItemRecyclerAdapter

        fabCategoryItem.setOnClickListener {

            val categoryItemInfoSheetFragment = CategoryItemInfoSheetFragment(this)
            categoryItemInfoSheetFragment.setCategoryId(categoryId)
            categoryItemInfoSheetFragment.show(supportFragmentManager, "CategoryItemInfoSheetFragment")

        }
    getCategoryItem()

    }

    //    region Categories
    fun categoryItemInsert(categoryItem: CategoryItem){
        categoryItemViewModel.insertCategoryItem(this,categoryItem)
    }

    fun deleteItemCategory(categoryItem: CategoryItem){
        categoryItemViewModel.deleteCategoryItem(this,categoryItem)
    }

    fun updateItemCategory(categoryItem: CategoryItem){
        categoryItemViewModel.updateCategoryItem(this , categoryItem)
    }

    fun getCategoryItem(){
        categoryItemViewModel.getByCategoryId(this,categoryId).observe(this, Observer { categoryItem->
            categoryItemRecyclerAdapter.setData(categoryItem)
        })
    }
//    endregion
fun showCategoryItemInfo(categoryItem: CategoryItem){
    val categoryItemInfoSheetFragment : CategoryItemInfoSheetFragment = CategoryItemInfoSheetFragment(this)
    categoryItemInfoSheetFragment.setAdding(false)
    categoryItemInfoSheetFragment.setCategoryId(categoryId)
    categoryItemInfoSheetFragment.setCurrentCategory(categoryItem)
    categoryItemInfoSheetFragment.show(supportFragmentManager,"CategoryInfoSheetFragment")
}

    fun moveToItemActivity(itemId :Int, name:String){
        val intent = Intent(this,DetailActivity::class.java).apply {
            putExtra("ItemId",itemId)
        }
        startActivity(intent)
    }



}