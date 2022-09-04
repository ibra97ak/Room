package com.ibra.mytarabisho.view.dialog

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.Nullable
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ibra.mytarabisho.R
import com.ibra.mytarabisho.model.entity.Category
import com.ibra.mytarabisho.model.entity.CategoryItem
import com.ibra.mytarabisho.view.activity.CategoryItemActivity
import com.ibra.mytarabisho.view.activity.MainActivity

class CategoryItemInfoSheetFragment(context : Context): BottomSheetDialogFragment() {

    private var isAdding: Boolean = true
    private var currenetCategoryItem: CategoryItem? = null
    private var categoryId: Int = -1
    private lateinit var edtCategoryItemInfoName: EditText
    private lateinit var btnCategoryItemInfoSave: Button
    private lateinit var btnCategoryItemInfoDelete: Button

    @Nullable
    @Override
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {

        val view: View = inflater.inflate(R.layout.dialog_fragment_category_item_info, null)

        edtCategoryItemInfoName = view.findViewById(R.id.edtCategoryItemInfoName)
        btnCategoryItemInfoSave = view.findViewById(R.id.btnCategoryItemInfoSave)
        btnCategoryItemInfoDelete = view.findViewById(R.id.btnCategoryItemInfoDelete)

        if (isAdding) {
            btnCategoryItemInfoSave.text = "Add"
            btnCategoryItemInfoDelete.visibility = View.GONE;
        } else {
            edtCategoryItemInfoName.setText(currenetCategoryItem!!.name)
            btnCategoryItemInfoSave.text = "Update"
            btnCategoryItemInfoDelete.visibility = View.VISIBLE;
        }
        btnCategoryItemInfoSave.setOnClickListener { v ->
            val name = edtCategoryItemInfoName.text.toString().trim()
            if (TextUtils.isEmpty(name))
            {
                Toast.makeText(getActivity(), "Please enter item name.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (isAdding)
            {
                val  categoryItem =  CategoryItem(0,name,categoryId)
                (activity as CategoryItemActivity).categoryItemInsert(categoryItem)
                dismiss()
            }
            else{
                val  categoryItem =  CategoryItem(currenetCategoryItem!!.id,name ,categoryId)
                (activity as CategoryItemActivity).updateItemCategory(categoryItem)
                Toast.makeText(getActivity(), "Updated Successfully", Toast.LENGTH_SHORT).show()
                dismiss()
            }
        }
        btnCategoryItemInfoDelete.setOnClickListener { v ->
            (activity as CategoryItemActivity).deleteItemCategory(currenetCategoryItem!!)
            Toast.makeText(getActivity(), "Deleted Successfully", Toast.LENGTH_SHORT).show()
            dismiss()
        }
        return view
    }

    fun setAdding(adding : Boolean){
        isAdding = adding
    }
    fun setCurrentCategory(currentCategoryItem: CategoryItem){
        this.currenetCategoryItem = currentCategoryItem
    }
    fun setCategoryId(categoryId : Int){
        this.categoryId = categoryId
    }

}