package com.ibra.mytarabisho.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ibra.mytarabisho.model.entity.Detail
import com.ibra.mytarabisho.repository.DetailRepository

class DetailViewModel :ViewModel() {

    private var readAllDetail : LiveData<List<Detail>>? =null

    private var readAllAmount : LiveData<List<Detail>>? =null
    private var readAllAmountById : List<Int>? =null

    fun insertDetail(context: Context,detail: Detail){
        DetailRepository.insertDetail(context , detail)
    }
    fun deleteDetail(context: Context , detail: Detail){
        DetailRepository.deleteDetail(context,detail)
    }
    fun updateDetail(context: Context,detail: Detail){
        DetailRepository.updateDetail(context,detail)
    }
    fun getAllDetail(context: Context):LiveData<List<Detail>>{
        readAllDetail = DetailRepository.getAllDetail(context)
        return readAllDetail!!
    }
    fun getDetailById(context: Context , id :Int):LiveData<List<Detail>>{
        readAllAmount = DetailRepository.getAllAmount(context,id)
        return readAllAmount!!
    }

}