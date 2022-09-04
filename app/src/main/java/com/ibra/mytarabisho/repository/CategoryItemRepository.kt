package com.ibra.mytarabisho.repository

import android.content.Context
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.LiveData
import com.ibra.mytarabisho.model.database.MyTarabishoDatabase
import com.ibra.mytarabisho.model.entity.CategoryItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryItemRepository {
    companion object{
        private var tarabishooDatabase: MyTarabishoDatabase? = null

        private var readAllCategoryItem : LiveData<List<CategoryItem>>? = null
        private var readByCategoryId : LiveData<List<CategoryItem>>? = null

        fun initializeDB(context: Context):MyTarabishoDatabase{
            return MyTarabishoDatabase.geDatabase(context)
        }

        fun addCategoryItem(context: Context,categoryItem: CategoryItem){
            tarabishooDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                tarabishooDatabase!!.categoryItemDao().addCategoryItem(categoryItem)
            }
        }

        fun deleteCategoryItem(context: Context,categoryItem: CategoryItem){
            tarabishooDatabase = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                tarabishooDatabase!!.categoryItemDao().deleteCategoryItem(categoryItem)
            }
        }
        fun updateCategoryItem(context: Context,categoryItem: CategoryItem){
            tarabishooDatabase = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                tarabishooDatabase!!.categoryItemDao().updateCategoryItem(categoryItem)
            }
        }
        fun getAllCategoryItem(context: Context):LiveData<List<CategoryItem>>{
            tarabishooDatabase = initializeDB(context)

            readAllCategoryItem = tarabishooDatabase!!.categoryItemDao().getAllCategoryItem()
            return readAllCategoryItem!!
        }
        fun getByCategoryId(context: Context, categoryId : Int):LiveData<List<CategoryItem>>{
            tarabishooDatabase = initializeDB(context)

            readByCategoryId = tarabishooDatabase!!.categoryItemDao().getByCategoryId(categoryId)
            return readByCategoryId!!
        }
    }
}