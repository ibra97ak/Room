package com.ibra.mytarabisho.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ibra.mytarabisho.model.entity.Category


@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCategory(category : Category)

    @Delete
    suspend fun deleteCategory(category : Category)

    @Update
    suspend fun updateCategory(category: Category)

    @Query("SELECT * FROM Category ORDER BY id ASC")
    fun getAllCategory() : LiveData<List<Category>>
}