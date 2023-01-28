package com.mudassir.giphyapi.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.mudassir.core.Resource
import com.mudassir.domain.model.GiphyDomainModel
import com.mudassir.domain.usecase.AddToFavouriteUseCase
import com.mudassir.domain.usecase.GetFavouriteGiphyUseCase
import com.mudassir.domain.usecase.GiphyTrendingUseCase
import com.mudassir.domain.usecase.RemoveFromFavouriteUseCase
import com.mudassir.giphyapi.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GiphyTrendingViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val observer = mockk<Observer<Resource<List<GiphyDomainModel>>>>(relaxed = true)

    private lateinit var giphyUseCase: GiphyTrendingUseCase
    private lateinit var viewModel: GiphyTrendingViewModel
    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var favouriteGiphyUseCase: GetFavouriteGiphyUseCase
    private lateinit var addToFavouriteUseCase: AddToFavouriteUseCase
    private lateinit var removeFromFavouriteUseCase: RemoveFromFavouriteUseCase

    private val domainList = listOf(
        GiphyDomainModel(
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

    @Before
    fun setUp() = coroutinesTestRule.testDispatcher.runBlockingTest {
        giphyUseCase = mockk()
        savedStateHandle = mockk(relaxed = true)
        favouriteGiphyUseCase = mockk()
        addToFavouriteUseCase = mockk()
        removeFromFavouriteUseCase = mockk()

        coEvery { giphyUseCase.executeAsync(any()) } returns Resource.success(
            domainList
        )
        viewModel = GiphyTrendingViewModel(
            savedStateHandle,
            giphyUseCase,
            favouriteGiphyUseCase,
            addToFavouriteUseCase,
            removeFromFavouriteUseCase
        )
    }

    @Test
    fun `Trending Giphy list will be fetched successfully`() {
        viewModel.giphyLiveData.observeForever(observer)

        viewModel.onEnter("")
        coVerify(exactly = 1) { giphyUseCase.executeAsync(any()) }


        coroutinesTestRule.testDispatcher.resumeDispatcher()

        val result = viewModel.giphyLiveData.value

        assertEquals(domainList, result?.data)
    }


    @Test
    fun `when giphy list emits error`() {
        viewModel.giphyLiveData.observeForever(observer)
        // given
        coEvery { giphyUseCase.executeAsync(null) } returns Resource.error("Something went wrong")

        // when
        viewModel.onEnter(null)

        // then
        coroutinesTestRule.testDispatcher.resumeDispatcher()

        val result = viewModel.giphyLiveData.value
        assertEquals("ERROR", result?.status?.name)
        assertEquals(null, result?.data)
    }

    @After
    fun tearDown() {
        viewModel.giphyLiveData.removeObserver(observer)
    }

}