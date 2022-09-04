package com.ibra.mytarabisho.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ibra.mytarabisho.model.entity.Detail

@Dao
interface DetailDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDetail(detail: Detail)

    @Delete
    suspend fun deleteDetail(detail: Detail)

    @Update
    suspend fun updateDetail(detail: Detail)

    @Query("SELECT * FROM Detail ORDER BY Id ASC")
    fun getAllDetail() : LiveData<List<Detail>>

    @Query("SELECT * FROM DETAIL WHERE ItemItemId = :id")
    fun getAmount(id : Int): LiveData<List<Detail>>

}