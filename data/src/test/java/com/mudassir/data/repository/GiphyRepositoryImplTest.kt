package com.mudassir.data.repository

import com.mudassir.data.CoroutinesTestRule
import com.mudassir.data.datasource.remote.GiphyTrendingRemoteDataSource
import com.mudassir.data.mapper.GiphyDataToDomainMapper
import com.mudassir.data.model.GiphyRemoteResponse
import com.mudassir.domain.model.GiphyDomainModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.mudassir.data.datasource.local.GiphyTrendingLocalDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
internal class GiphyRepositoryImplTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val dataSource = mockk<GiphyTrendingRemoteDataSource>()
    private val localDataSource = mockk<GiphyTrendingLocalDataSource>()
    private val mapper = mockk<GiphyDataToDomainMapper>()

    @Before
    fun setUp() {
        // no-op
    }

    @Test
    fun `GiphyRepository returns list of giphy trending list`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            coEvery { dataSource.getTrendingGiphys(any()) } returns
                    GiphyRemoteResponse(
                        listOf(
                            GiphyRemoteResponse.GiphyData(
                                id = "123455435",
                                title = "Giphy Title",
                                rating = "G",
                                images = GiphyRemoteResponse.GiphyData.Images(
                                    fixedHeight = GiphyRemoteResponse.GiphyData.Images.FixedHeight(
                                        url = "testurl"
                                    )
                                ),
                                type = "gif"
                            )
                        )
                    )

            coEvery { mapper.invoke(any()) } returns listOf(
                GiphyDomainModel(
                    id = "123455435",
                    title = "Giphy Title",
                    rating = "G",
                    url = "testurl",
                    type = "gif"
                )
            )

            val repository = GiphyRepositoryImpl(dataSource, localDataSource, mapper)

            repository.getTrendingGiphy(QUERY)

            coVerify { dataSource.getTrendingGiphys(any()) }
            coVerify { mapper.invoke(any()) }

            assertThat(repository.getTrendingGiphy(QUERY)).isNotNull()
            assertThat(repository.getTrendingGiphy(QUERY)).isEqualTo(
                listOf(
                    GiphyDomainModel(
                        "123455435",
                        title = "Giphy Title",
                        rating = "G",
                        url = "testurl",
                        type = "gif"
                    )
                )
            )
        }

    @After
    fun tearDown() {
        // no-op
    }

    companion object {
        private const val QUERY = "app"
    }
}