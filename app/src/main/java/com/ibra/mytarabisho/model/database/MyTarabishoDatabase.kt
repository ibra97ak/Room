package com.ibra.mytarabisho.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ibra.mytarabisho.model.dao.CategoryDao
import com.ibra.mytarabisho.model.dao.CategoryItemDao
import com.ibra.mytarabisho.model.dao.DetailDao
import com.ibra.mytarabisho.model.entity.Category
import com.ibra.mytarabisho.model.entity.CategoryItem
import com.ibra.mytarabisho.model.entity.Detail

@Database(entities = [Category::class,CategoryItem::class,Detail::class], version = 1, exportSchema = false)
abstract class MyTarabishoDatabase() :RoomDatabase(){

    abstract fun categoryDao() : CategoryDao
    abstract fun categoryItemDao() : CategoryItemDao
    abstract fun detailDao() : DetailDao

    companion object{

        @Volatile
        private var INSTANCE: MyTarabishoDatabase? = null

        fun geDatabase(context : Context) : MyTarabishoDatabase{

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    MyTarabishoDatabase::class.java,
                    "Tarabisho_Database"
                ).fallbackToDestructiveMigration()
                    .build()
                return INSTANCE!!
            }
        }
    }
}