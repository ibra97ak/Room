package com.ibra.mytarabisho.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "Category")
data class Category(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    val id: Int,
    @ColumnInfo(name = "Name")
    val name: String,
)
