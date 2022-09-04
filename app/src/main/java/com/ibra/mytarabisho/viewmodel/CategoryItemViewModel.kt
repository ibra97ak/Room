package com.ibra.mytarabisho.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ibra.mytarabisho.model.entity.CategoryItem
import com.ibra.mytarabisho.repository.CategoryItemRepository

class CategoryItemViewModel:ViewModel() {

    private  var readAllData: LiveData<List<CategoryItem>>? = null

    private  var readByCategoryId: LiveData<List<CategoryItem>>? = null

    fun insertCategoryItem (context: Context,categoryItem: CategoryItem){
        CategoryItemRepository.addCategoryItem(context,categoryItem)
    }

    fun updateCategoryItem(context: Context,categoryItem: CategoryItem){
        CategoryItemRepository.updateCategoryItem(context,categoryItem)
    }

    fun deleteCategoryItem(context: Context,categoryItem: CategoryItem){
        CategoryItemRepository.deleteCategoryItem(context,categoryItem)
    }

    fun getAllCategoryItem(context: Context):LiveData<List<CategoryItem>>{
        readAllData = CategoryItemRepository.getAllCategoryItem(context)

        return readAllData!!
    }
    fun getByCategoryId(context: Context,categoryId : Int):LiveData<List<CategoryItem>>{
        readByCategoryId = CategoryItemRepository.getByCategoryId(context,categoryId)

        return readByCategoryId!!
    }
}