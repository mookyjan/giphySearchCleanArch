package com.mudassir.data.datasource.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mudassir.data.datasource.local.dao.GiphyDao
import com.mudassir.data.datasource.local.model.GiphyEntityModel

@Database(entities = [GiphyEntityModel::class], version = 1, exportSchema = false)
internal abstract class GiphyDatabase : RoomDatabase() {

    abstract fun giphys(): GiphyDao

    companion object{
        fun newInstance(context: Context): GiphyDatabase {
            return Room.databaseBuilder(context,
                GiphyDatabase::class.java,
                "giphy.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}