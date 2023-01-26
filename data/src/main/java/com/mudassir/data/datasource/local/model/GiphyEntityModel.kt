package com.mudassir.data.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mudassir.domain.model.GiphyDomainModel

@Entity(tableName = "giphys")
internal data class GiphyEntityModel(
    @PrimaryKey
    val id: String,
    val title: String,
    val rating: String,
    val url: String,
    val type: String,
    val isFavourite: Boolean
)


internal fun GiphyEntityModel.toDomainModel() =
    GiphyDomainModel(
        id = this.id,
        title = this.title,
        rating = this.rating,
        url = this.url,
        type = this.type
    )