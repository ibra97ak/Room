package com.ibra.mytarabisho.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
@Entity(tableName = "CategoryItem", foreignKeys =[
    ForeignKey(
        entity = Category::class,
        parentColumns = ["Id"],
        childColumns = ["CategoryId"]
    )]
)
class CategoryItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id", typeAffinity = ColumnInfo.INTEGER)
    val id: Int,
    @ColumnInfo(name = "Name", typeAffinity = ColumnInfo.TEXT)
    val name: String,
    @ColumnInfo(name = "CategoryId", typeAffinity = ColumnInfo.INTEGER)
    val categoryId: Int

) {
}