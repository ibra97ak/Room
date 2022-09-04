package com.ibra.mytarabisho.repository

import android.content.ClipData
import android.content.Context
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.LiveData
import com.ibra.mytarabisho.model.database.MyTarabishoDatabase
import com.ibra.mytarabisho.model.entity.Detail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailRepository {
    companion object{

        private var tarabishooDatabase : MyTarabishoDatabase? =null

        private var getAllDetail : LiveData<List<Detail>>? =null

        private var getAllAmount : LiveData<List<Detail>>? =null
        private var getAllAmountById :List<Int> ? =null

        fun initializeDb(context: Context):MyTarabishoDatabase{
            return MyTarabishoDatabase.geDatabase(context)
        }

        fun insertDetail(context: Context,detail: Detail){
            tarabishooDatabase = initializeDb(context)
            CoroutineScope(Dispatchers.IO).launch {
                tarabishooDatabase!!.detailDao().insertDetail(detail)
            }
        }

        fun deleteDetail(context: Context,detail: Detail){
            tarabishooDatabase = initializeDb(context)

            CoroutineScope(Dispatchers.IO).launch {
                tarabishooDatabase!!.detailDao().deleteDetail(detail)
            }
        }

        fun updateDetail(context: Context,detail:Detail){
            tarabishooDatabase = initializeDb(context)

            CoroutineScope(Dispatchers.IO).launch {
                tarabishooDatabase!!.detailDao().updateDetail(detail)
            }
        }

        fun getAllDetail(context: Context):LiveData<List<Detail>>{
            tarabishooDatabase = initializeDb(context)

            getAllDetail = tarabishooDatabase!!.detailDao().getAllDetail()

            return getAllDetail!!
        }

        fun getAllAmount(context: Context,id:Int): LiveData<List<Detail>> {
            tarabishooDatabase = initializeDb(context)

            getAllAmount = tarabishooDatabase!!.detailDao().getAmount(id)

            return getAllAmount!!
        }


    }
}