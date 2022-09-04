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

class CategoryItemRecyclerAdapter :RecyclerView.Adapter<CategoryItemRecyclerAdapter.CategoryItemViewHolder> {
    private var myList : List<CategoryItem>? = null
    private var context : Context? =null

    inner class CategoryItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val txtCategoryItemName =itemView.findViewById<TextView>(R.id.txtCategoryItemName)
    }
    constructor(context: Context,myList : List<CategoryItem>){
        this.context = context
        this.myList = myList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_category_item,parent,false)
        return CategoryItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        val categoryItem : CategoryItem = myList!![position]

        holder.txtCategoryItemName.text = categoryItem.name

        holder.itemView.setOnLongClickListener {
            (context as CategoryItemActivity).showCategoryItemInfo(categoryItem)
            return@setOnLongClickListener true
        }

        holder.itemView.setOnClickListener {
            (context as CategoryItemActivity).moveToItemActivity(categoryItem.id,categoryItem.name)
        }
    }

    override fun getItemCount(): Int {
        return myList!!.size
    }

    fun setData(categoryItem: List<CategoryItem>){
       this.myList = categoryItem
        notifyDataSetChanged()
    }

}