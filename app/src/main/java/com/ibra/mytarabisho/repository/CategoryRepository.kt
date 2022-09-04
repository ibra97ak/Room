package com.ibra.mytarabisho.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.ibra.mytarabisho.model.database.MyTarabishoDatabase
import com.ibra.mytarabisho.model.entity.Category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryRepository {
    companion object {

       private var tarabishooDatabase: MyTarabishoDatabase? = null

        private var readAllCategory : LiveData<List<Category>>? =null

        fun initializeDB(context: Context): MyTarabishoDatabase {
            return MyTarabishoDatabase.geDatabase(context)
        }

        fun insertData(context: Context, category : Category) {

            tarabishooDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                tarabishooDatabase!!.categoryDao().addCategory(category)
            }

        }

        fun getAllCategory (context: Context): LiveData<List<Category>>{
            tarabishooDatabase = initializeDB(context)

            readAllCategory = tarabishooDatabase!!.categoryDao().getAllCategory()

            return readAllCategory!!

        }

        fun deleteCategory(context: Context, category: Category){
            initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                tarabishooDatabase!!.categoryDao().deleteCategory(category)
            }
        }

        fun updateCategory(context: Context,category: Category){
            tarabishooDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                tarabishooDatabase!!.categoryDao().updateCategory(category)
            }
        }

    }
}