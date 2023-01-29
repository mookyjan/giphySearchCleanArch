package com.mudassir.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mudassir.data.datasource.remote.GiphyTrendingRemoteDataSource
import com.mudassir.data.mapper.GiphyDataToDomainMapper
import com.mudassir.domain.model.GiphyDomainModel
import retrofit2.HttpException
import java.io.IOException

private const val GIPHY_STARTING_PAGE_INDEX = 1
internal class PagingDataSource (private val giphyTrendingRemoteDataSource: GiphyTrendingRemoteDataSource,
                                 private val query: String?,
                                 private val giphyDataToDomainMapper: GiphyDataToDomainMapper
):
    PagingSource<Int, GiphyDomainModel>() {

    override fun getRefreshKey(state: PagingState<Int, GiphyDomainModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GiphyDomainModel> {
        val position = params.key ?: GIPHY_STARTING_PAGE_INDEX
        Log.d("TAG Pagi", "load: $position   $params")
        return try {
            val response = giphyTrendingRemoteDataSource.getTrendingGiphys(query,position)
           val totalCount = response.pagination?.totalCount
            val listing = giphyDataToDomainMapper.invoke(response)
            LoadResult.Page(
                data = listing ?: emptyList(),
                prevKey = if (position == GIPHY_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (position == totalCount || totalCount == 0) null else position + 1
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