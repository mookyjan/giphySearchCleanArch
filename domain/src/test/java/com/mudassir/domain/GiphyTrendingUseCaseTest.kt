package com.mudassir.domain

import com.google.common.truth.Truth.assertThat
import com.mudassir.core.Resource
import com.mudassir.core.util.ErrorFactory
import com.mudassir.domain.model.GiphyDomainModel
import com.mudassir.domain.repository.GiphyRepository
import com.mudassir.domain.usecase.GiphyTrendingUseCase
import com.mudassir.domain.util.CoroutinesTestRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GiphyTrendingUseCaseTest {
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @MockK
    private lateinit var repository: GiphyRepository

    @MockK
    private lateinit var factory: ErrorFactory

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `GiphyTrendingUseCase returns giphy list from remote data`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery { repository.getTrendingGiphy(any()) } returns listOf(
                GiphyDomainModel(
                    "1232534we",
                    title = "Giphy",
                    rating = "g",
                    url = "testurl",
                    type = "gif"
                )
            )

            every { factory.createApiErrorMessage(any()) } returns ""

            val useCase = GiphyTrendingUseCase(repository, factory)

            useCase.executeAsync(QUERY)

            coVerify { repository.getTrendingGiphy(any()) }

            assertThat(useCase.executeAsync(QUERY)).isNotNull()
            assertThat(useCase.executeAsync(QUERY)).isEqualTo(
                Resource.success(
                    listOf(
                        GiphyDomainModel(
                            "1232534we",
                            title = "Giphy",
                            rating = "g",
                            url = "testurl",
                            type = "gif"
                        )
                    )
                )
            )
        }


    @Test
    fun `GiphyTrendingUseCase throws error`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery { repository.getTrendingGiphy(any()) } throws NullPointerException("null")

        every { factory.createApiErrorMessage(any()) } returns "null"

        val useCase = GiphyTrendingUseCase(repository, factory)

        useCase.executeAsync(QUERY)

        verify { factory.createApiErrorMessage(any()) }

        assertThat(useCase.executeAsync(QUERY)).isNotNull()
        assertThat(useCase.executeAsync(QUERY)).isEqualTo(
            Resource.error<NullPointerException>(
                "null"
            )
        )
    }

    @After
    fun tearDown() {
        // no-op
    }

    companion object {
        private const val QUERY = "apple"
    }
}