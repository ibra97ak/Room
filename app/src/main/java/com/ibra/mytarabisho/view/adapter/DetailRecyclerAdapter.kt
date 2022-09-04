package com.ibra.mytarabisho.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ibra.mytarabisho.R
import com.ibra.mytarabisho.model.entity.CategoryItem
import com.ibra.mytarabisho.model.entity.Detail
import com.ibra.mytarabisho.view.activity.CategoryItemActivity
import com.ibra.mytarabisho.view.activity.DetailActivity

class DetailRecyclerAdapter : RecyclerView.Adapter<DetailRecyclerAdapter.ItemViewHolder> {
    private var myList : List<Detail>? = null
    private var context : Context? =null
    private var amount : Int = 0

    inner class ItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val txtItemDate =itemView.findViewById<TextView>(R.id.txtDate)
        val txtItemTaking =itemView.findViewById<TextView>(R.id.txtTaking)
        val txtItemAdding =itemView.findViewById<TextView>(R.id.txtAdding)
        val txtItemAmount =itemView.findViewById<TextView>(R.id.txtTotal)

    }
    constructor(context: Context,myList : List<Detail>){
        this.context = context
        this.myList = myList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_detail,parent,false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val detail : Detail = myList!![position]

        holder.txtItemDate.text = "2022/"+detail.date
        holder.txtItemTaking.text = detail.taking.toString()
        holder.txtItemAdding.text = detail.adding.toString()
        if (amount == 0){
            amount = detail.amount
            holder.txtItemAmount.text = amount.toString()
        }
        else{
            amount = amount+detail.adding-detail.taking
            holder.txtItemAmount.text = amount.toString()
        }


        holder.itemView.setOnLongClickListener {
            (context as DetailActivity).showItemInfoSheetFragment(detail)
            return@setOnLongClickListener true
        }

    }

    override fun getItemCount(): Int {
        return myList!!.size
    }
    fun setData(detail: List<Detail>){
        this.myList = detail
        notifyDataSetChanged()
    }
    fun setAmount(){
        this.amount = 0
    }

}