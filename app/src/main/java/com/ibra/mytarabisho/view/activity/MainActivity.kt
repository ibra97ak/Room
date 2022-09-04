package com.ibra.mytarabisho.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.github.clans.fab.FloatingActionButton
import com.ibra.mytarabisho.view.dialog.CategoryInfoSheetFragment
import com.ibra.mytarabisho.R
import com.ibra.mytarabisho.model.entity.Category
import com.ibra.mytarabisho.view.adapter.CategoryRecyclerAdapter
import com.ibra.mytarabisho.viewmodel.CategoriesViewModel
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var categoriesViewModel: CategoriesViewModel

    private lateinit var fab : FloatingActionButton

    private lateinit var recyclerMainCategories: RecyclerView
    private lateinit var categoryRecyclerAdapter: CategoryRecyclerAdapter
    private var categories: List<Category> = ArrayList<Category>()
    lateinit var layoutManager: GridLayoutManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab = findViewById(R.id.fab)
        categoriesViewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)

        fab.setOnClickListener {
            val categoryInfoSheetFragment = CategoryInfoSheetFragment(this)
            categoryInfoSheetFragment.show(supportFragmentManager, "CategoryInfoSheetFragment")

        }

        recyclerMainCategories = findViewById(R.id.recyclerMainCategories)
        layoutManager = GridLayoutManager(this, 2)
        categoryRecyclerAdapter = CategoryRecyclerAdapter(this,categories)
        recyclerMainCategories.adapter = categoryRecyclerAdapter
        recyclerMainCategories.layoutManager = layoutManager

        getCategory()

    }
//    region Categories
        fun categoryInsert(category : Category){
        categoriesViewModel.insertData(this,category)
    }

    fun deleteCategory(category: Category){
        categoriesViewModel.deleteCategory(this,category)
    }

    fun updateCategory(category: Category){
        categoriesViewModel.updateCategory(this , category)
    }

    fun getCategory(){
        categoriesViewModel.getAllData(this).observe(this, Observer { categories->
            categoryRecyclerAdapter.setData(categories)
        })
    }
//    endregion

    fun showCategoryInfo(category: Category){
        val categoryInfoSheetFragment : CategoryInfoSheetFragment = CategoryInfoSheetFragment(this)
        categoryInfoSheetFragment.setAdding(false)
        categoryInfoSheetFragment.setCurrentCategory(category)
        categoryInfoSheetFragment.show(supportFragmentManager,"CategoryInfoSheetFragment")
    }

    fun newActivity(id : Int){
        val intent = Intent(this, CategoryItemActivity::class.java).apply {
            putExtra("categoryId", id)
        }
       startActivity(intent)
    }

}