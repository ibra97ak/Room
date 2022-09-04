package com.ibra.mytarabisho.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ibra.mytarabisho.model.entity.Category
import com.ibra.mytarabisho.repository.CategoryRepository

class CategoriesViewModel  : ViewModel() {

    private  var readAllData: LiveData<List<Category>>? = null


    fun insertData(context: Context, category: Category) {
        CategoryRepository.insertData(context,category)
    }

    fun getAllData(context: Context):LiveData<List<Category>>{
        readAllData = CategoryRepository.getAllCategory(context)

        return readAllData!!
    }

    fun deleteCategory(context: Context,category: Category){
        CategoryRepository.deleteCategory(context,category )
    }

    fun updateCategory(context: Context,category: Category){
        CategoryRepository.updateCategory(context,category)
    }

}