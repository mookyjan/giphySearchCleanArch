package com.mudassir.data.mapper

import com.mudassir.data.CoroutinesTestRule
import com.mudassir.data.model.GiphyRemoteResponse
import com.mudassir.domain.model.GiphyDomainModel
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class GiphyDataToDomainMapperTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()
    private val mapper = mockk<GiphyDataToDomainMapper>()

    @Before
    fun setUp() {
        // no-op
    }

    @Test
    fun `GiphyDataToDomainMapper maps raw data to DomainModel`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            val slot = slot<GiphyRemoteResponse>()

            val domainList = listOf(GiphyDomainModel(
                id = "123455435",
                title = "Giphy Title",
                rating = "G",
                url = "testurl",
                type = "gif"
            ),
                GiphyDomainModel(
                    id = "123455435Abc",
                    title = "Giphy Title 2",
                    rating = "G",
                    url = "testurl2",
                    type = "gif"
                ),
            )

            coEvery { mapper.invoke(capture(slot)) } returns domainList

            val dataResponse = GiphyRemoteResponse(
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
            val invocation = mapper.invoke(dataResponse)
            assertThat(invocation).isNotNull()
            assertThat(invocation).isEqualTo(
              domainList
            )
            assertThat(slot.captured).isEqualTo(
                dataResponse
            )
        }

    @After
    fun tearDown() {
        // no-op
    }
}