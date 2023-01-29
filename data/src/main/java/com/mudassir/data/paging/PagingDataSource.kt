package com.mudassir.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mudassir.data.datasource.remote.GiphyTrendingRemoteDataSource
import com.mudassir.data.mapper.GiphyDataToDomainMapper
import com.mudassir.domain.model.GiphyDomainModel
import retrofit2.HttpException
import java.io.IOException

internal class PagingDataSource (private val giphyTrendingRemoteDataSource: GiphyTrendingRemoteDataSource,
                                 private val query: String?,
                                 private val giphyDataToDomainMapper: GiphyDataToDomainMapper
):
    PagingSource<Int, GiphyDomainModel>() {

    override fun getRefreshKey(state: PagingState<Int, GiphyDomainModel>): Int? {
       return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GiphyDomainModel> {
        val position = params.key ?: 1
        return try {
            val response = giphyTrendingRemoteDataSource.getTrendingGiphys(query, params.loadSize,position)
            val listing = giphyDataToDomainMapper.invoke(response)
            LoadResult.Page(
                data = listing ?: listOf(),
                prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        } catch (ex: Exception){
            return LoadResult.Error(ex)
        }
    }

    override val keyReuseSupported: Boolean = true
}