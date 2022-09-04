package com.ibra.mytarabisho.view.dialog

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
import com.ibra.mytarabisho.model.entity.Detail
import com.ibra.mytarabisho.view.activity.DetailActivity

class ItemInfoSheetFragment (): BottomSheetDialogFragment() {
    private var isAdding: Boolean = true
    private var currenetItem: Detail? = null
    private var itemId: Int = -1

    private lateinit var edtItemInfoDate: EditText
    private lateinit var edtItemInfoAdding: EditText
    private lateinit var edtItemInfoTaking: EditText
    private lateinit var btnItemInfoSave: Button
    private lateinit var btnItemInfoDelete: Button


    @Nullable
    @Override
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {

        val view: View = inflater.inflate(R.layout.dialog_fragment_item_info, null)

        edtItemInfoDate = view.findViewById(R.id.edtItemDate)
        edtItemInfoAdding = view.findViewById(R.id.edtItemAdding)
        edtItemInfoTaking = view.findViewById(R.id.edtItemTaking)
        btnItemInfoSave = view.findViewById(R.id.btnItemInfoSave)
        btnItemInfoDelete = view.findViewById(R.id.btnItemInfoDelete)

        if (isAdding){
            btnItemInfoSave.text = "Add"
            btnItemInfoDelete.visibility = View.GONE
        }
        else{
            edtItemInfoDate.setText(currenetItem!!.date)
            edtItemInfoAdding.setText(currenetItem!!.adding.toString())
            edtItemInfoTaking.setText(currenetItem!!.taking.toString())
            btnItemInfoSave.text = "Save"
            btnItemInfoDelete.visibility = View.VISIBLE
        }

        btnItemInfoSave.setOnClickListener { v ->
            val date = edtItemInfoDate.text.toString().trim()
            var adding = edtItemInfoAdding.text.toString().trim()
            var taking = edtItemInfoTaking.text.toString().trim()

            if (TextUtils.isEmpty(date)) {
                Toast.makeText(getActivity(), "Please enter the date.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(adding) && TextUtils.isEmpty(taking)) {
                Toast.makeText(getActivity(), "Please enter your Data.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(taking) && !TextUtils.isEmpty(adding) ) {
                taking ="0"
            }
            else if (TextUtils.isEmpty(adding) && !TextUtils.isEmpty(taking) ) {
                adding ="0"
            }

            val total = adding.toInt() - taking.toInt()

                if (isAdding) {
                    val detail =
                        Detail(0, total, date, adding.toInt(), taking.toInt(), itemId)
                    (activity as DetailActivity).insertDetail(detail)
                    (activity as DetailActivity).setAmount()
                    dismiss()
                } else {
                    val detail = Detail(currenetItem!!.id,
                        total,
                        date,
                        adding.toInt(),
                        taking.toInt(),
                        itemId)
                    (activity as DetailActivity).updateDetail(detail)
                    Toast.makeText(getActivity(), "Updated Successfully", Toast.LENGTH_SHORT).show()
                    (activity as DetailActivity).setAmount()
                    dismiss()
                }
            }


        btnItemInfoDelete.setOnClickListener { v ->
            (activity as DetailActivity).deleteDetail(currenetItem!!)
            Toast.makeText(getActivity(), "Deleted Successfully", Toast.LENGTH_SHORT).show()
            (activity as DetailActivity).setAmount()
            dismiss()
        }

        return view
    }
    fun setAdding(adding : Boolean){
        isAdding = adding
    }
    fun setCurrentDetail(currenetItem: Detail){
        this.currenetItem = currenetItem
    }
    fun setItemId(itemId : Int){
        this.itemId = itemId
    }

}