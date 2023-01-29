package com.mudassir.data.datasource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mudassir.data.datasource.local.model.GiphyEntityModel

@Dao
internal interface GiphyDao {

    @Query("SELECT * FROM giphys")
    fun getAll(): LiveData<List<GiphyEntityModel>>

    @Query("SELECT * FROM giphys where title = :title")
    suspend fun getGiphy(title: String): GiphyEntityModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllGiphy(giphys: List<GiphyEntityModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavourite(giphy: GiphyEntityModel)

    @Delete
    suspend fun removeFromFavourite(giphy: GiphyEntityModel)


}