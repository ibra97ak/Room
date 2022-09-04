package com.ibra.mytarabisho.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Detail", foreignKeys =[
    ForeignKey(
        entity = CategoryItem::class,
        parentColumns = ["Id"],
        childColumns = ["ItemItemId"]
    )]
)
class Detail(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id", typeAffinity = ColumnInfo.INTEGER)
    val id: Int,
    @ColumnInfo(name = "Amount", typeAffinity = ColumnInfo.INTEGER)
    val amount: Int,
    @ColumnInfo(name = "Date", typeAffinity = ColumnInfo.TEXT)
    val date: String,
    @ColumnInfo(name = "Adding", typeAffinity = ColumnInfo.INTEGER)
    val adding: Int,
    @ColumnInfo(name = "Taking", typeAffinity = ColumnInfo.INTEGER)
    val taking: Int,
    @ColumnInfo(name = "ItemItemId", typeAffinity = ColumnInfo.INTEGER)
    val itemItemId: Int
) {
}