package com.ibra.mytarabisho.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ibra.mytarabisho.R
import com.ibra.mytarabisho.model.entity.Category
import com.ibra.mytarabisho.view.activity.MainActivity

class CategoryRecyclerAdapter : RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder> {

    private var myList : List<Category>? = null
    private var context : Context? =null

    inner class CategoryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val txtCategoryName =itemView.findViewById<TextView>(R.id.txtCategoryName)


    }
    constructor(context: Context,myList : List<Category>){
        this.context = context
        this.myList = myList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_category,parent,false)
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category : Category = myList!![position]

        holder.txtCategoryName.text = category.name

        holder.itemView.setOnLongClickListener{
            (context as MainActivity).showCategoryInfo(category)
            return@setOnLongClickListener true
        }
        holder.itemView.setOnClickListener {
            (context as MainActivity).newActivity(category.id)
        }
    }

    override fun getItemCount(): Int {
        return myList!!.size
    }


    fun setData (category : List<Category>){
        this.myList = category
        notifyDataSetChanged()
    }
}