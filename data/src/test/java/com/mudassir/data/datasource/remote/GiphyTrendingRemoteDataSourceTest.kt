package com.mudassir.data.datasource.remote

import com.google.common.truth.Truth.assertThat
import com.mudassir.data.CoroutinesTestRule
import com.mudassir.data.datasource.remote.service.GiphyService
import com.mudassir.data.model.GiphyRemoteResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GiphyTrendingRemoteDataSourceTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()
    private val api = mockk<GiphyService>()
    private var QUERY = ""
    private val dataResponse = GiphyRemoteResponse(
        listOf(
            GiphyRemoteResponse.GiphyData(
                id = "123455435",
                title = "Giphy Title",
                rating = "G",
                images = GiphyRemoteResponse.GiphyData.Images(
                    fixedHeight = GiphyRemoteResponse.GiphyData.Images.FixedHeight(url = "testurl")
                ),
                type = "gif"
            ),
            GiphyRemoteResponse.GiphyData(
                id = "123455435Abc",
                title = "Giphy Title 2",
                rating = "G",
                images = GiphyRemoteResponse.GiphyData.Images(
                    fixedHeight = GiphyRemoteResponse.GiphyData.Images.FixedHeight(url = "testurl2")
                ),
                type = "gif"
            )
        )
    )

    @Before
    fun setUp() {
        // no-op
    }

    @Test
    fun `GiphyRemoteDataSource fetches list of trending api when query is empty or null`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            //Given
            coEvery { api.getTrendingGiphys(any()) } returns dataResponse

            val dataSource = GiphyTrendingRemoteDataSource(api)

            //when
            QUERY =""
            val invocation = dataSource.getTrendingGiphys(QUERY)

            //Then
            coVerify { api.getTrendingGiphys(any()) }

            assertThat(invocation).isNotNull()
            assertThat(invocation).isEqualTo(
                dataResponse
            )
        }

    @Test
    fun `GiphyRemoteDataSource fetches list of search api when query is not null or empty`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            //Given
            coEvery { api.searchForGiphys(any()) } returns dataResponse

            val dataSource = GiphyTrendingRemoteDataSource(api)

            //when
            QUERY ="Apple"
            val invocation = dataSource.getTrendingGiphys(QUERY)

            //Then
            coVerify { api.searchForGiphys(any()) }

            assertThat(invocation).isNotNull()
            assertThat(invocation).isEqualTo(
                dataResponse
            )
        }

    @After
    fun tearDown() {
        // no-op
    }
}