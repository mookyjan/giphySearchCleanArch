package com.mudassir.data.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mudassir.domain.model.GiphyDomainModel

@Entity(tableName = "giphys")
internal data class GiphyEntityModel (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val rating: String,
    val url: String,
    val isFavourite: Boolean
)


internal fun GiphyEntityModel.toDomainModel() =
    GiphyDomainModel(
        title = this.title,
        rating = this.rating,
        url = this.url,
        type = null
    )