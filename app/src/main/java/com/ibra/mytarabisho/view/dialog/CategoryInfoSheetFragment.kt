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
import com.ibra.mytarabisho.view.activity.MainActivity

class CategoryInfoSheetFragment (context: Context): BottomSheetDialogFragment() {
    private var isAdding: Boolean = true
    private var currenetCategory: Category? = null


    private lateinit var edtCategoryInfoName: EditText
    private lateinit var btnCategoryInfoSave: Button
    private lateinit var btnCategoryInfoDelete: Button

    @Nullable
    @Override
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {

        val view: View = inflater.inflate(R.layout.dialog_fragment_category_info, null)


        edtCategoryInfoName = view.findViewById(R.id.edtCategoryInfoName)
        btnCategoryInfoSave = view.findViewById(R.id.btnCategoryInfoSave)
        btnCategoryInfoDelete = view.findViewById(R.id.btnCategoryInfoDelete)





        if (isAdding) {
            btnCategoryInfoSave.text = "Add";
            btnCategoryInfoDelete.visibility = View.GONE;
        } else {
            edtCategoryInfoName.setText(currenetCategory!!.name)
            btnCategoryInfoSave.text = "Update";
            btnCategoryInfoDelete.visibility = View.VISIBLE;
        }

        btnCategoryInfoSave.setOnClickListener { v ->
            val name = edtCategoryInfoName.text.toString().trim()
            if (TextUtils.isEmpty(name))
            {
                Toast.makeText(getActivity(), "Please enter category name.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (isAdding)
            {
                val  category =  Category(0,name)
                (activity as MainActivity).categoryInsert(category)
                dismiss()
            }
            else{
                val  category =  Category(currenetCategory!!.id,name)
                (activity as MainActivity).updateCategory(category)
                Toast.makeText(getActivity(), "Updated Successfully", Toast.LENGTH_SHORT).show()
                dismiss()
            }
        }
        btnCategoryInfoDelete.setOnClickListener { v ->
            (activity as MainActivity).deleteCategory(currenetCategory!!)
            Toast.makeText(getActivity(), "Deleted Successfully", Toast.LENGTH_SHORT).show()
            dismiss()
        }
        return view
    }

    fun setAdding(adding : Boolean){
        isAdding = adding
    }
    fun setCurrentCategory(currentCategory: Category){
        this.currenetCategory = currentCategory
    }
}