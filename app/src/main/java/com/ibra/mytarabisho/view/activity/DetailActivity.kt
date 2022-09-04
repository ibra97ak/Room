package com.ibra.mytarabisho.view.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.github.clans.fab.FloatingActionButton
import com.ibra.mytarabisho.R
import com.ibra.mytarabisho.model.entity.Detail
import com.ibra.mytarabisho.view.adapter.DetailRecyclerAdapter
import com.ibra.mytarabisho.view.dialog.ItemInfoSheetFragment
import com.ibra.mytarabisho.viewmodel.DetailViewModel
import java.util.ArrayList

class DetailActivity : AppCompatActivity() {

    private lateinit var fabItem : FloatingActionButton
    private var itemId :Int = 0
    private lateinit var detailViewModel : DetailViewModel

    private lateinit var recyclerDetail: RecyclerView
    private lateinit var detailRecyclerAdapter: DetailRecyclerAdapter
    private var details: List<Detail> = ArrayList<Detail>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        fabItem = findViewById(R.id.fabItem)
        itemId = intent.getIntExtra("ItemId",-1)
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)


        recyclerDetail = findViewById(R.id.recyclerDetail)
        detailRecyclerAdapter = DetailRecyclerAdapter(this,details)
        recyclerDetail.adapter = detailRecyclerAdapter

        fabItem.setOnClickListener {
            val itemInfoSheetFragment = ItemInfoSheetFragment()
            itemInfoSheetFragment.setItemId(itemId)
            itemInfoSheetFragment.show(supportFragmentManager,"ItemInfoSheetFragment")
        }

        getDetailById()
    }

    //region Item
    fun insertDetail(detail: Detail){
        detailViewModel.insertDetail(this,detail)
    }

    fun deleteDetail(detail: Detail){
        detailViewModel.deleteDetail(this,detail)
    }
    fun updateDetail(detail: Detail){
        detailViewModel.updateDetail(this,detail)
    }
    fun getDetailById(){
        detailViewModel.getDetailById(this, itemId).observe(this,{Detail->
            detailRecyclerAdapter.setData(Detail)

        })
    }


    fun showItemInfoSheetFragment(detail: Detail) {
        val itemInfoSheetFragment = ItemInfoSheetFragment()
        itemInfoSheetFragment.setItemId(itemId)
        itemInfoSheetFragment.setAdding(false)
        itemInfoSheetFragment.setCurrentDetail(detail)
        itemInfoSheetFragment.show(supportFragmentManager,"ItemInfoSheetFragment")

    }

    //endregion

  fun setAmount(){
      detailRecyclerAdapter.setAmount()
  }

}
