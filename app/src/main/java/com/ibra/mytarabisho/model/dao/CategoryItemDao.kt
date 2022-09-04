package com.ibra.mytarabisho.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ibra.mytarabisho.model.entity.CategoryItem

@Dao
interface CategoryItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCategoryItem(categoryItem: CategoryItem)

    @Delete
    suspend fun deleteCategoryItem(categoryItem: CategoryItem)

    @Update
    suspend fun updateCategoryItem(categoryItem: CategoryItem)

    @Query("SELECT * FROM CategoryItem ORDER BY Id ASC")
    fun getAllCategoryItem() : LiveData<List<CategoryItem>>

   @Query("SELECT * FROM CategoryItem WHERE CategoryId = :categoryId")
    fun getByCategoryId(categoryId : Int): LiveData<List<CategoryItem>>

//    @Query("SELECT Amount FROM  WHERE CategoryId = :id")
//    fun getCategoryItemById(id : Int)


}