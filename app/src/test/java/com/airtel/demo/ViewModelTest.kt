package com.airtel.demo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.airtel.demo.data.network.NetworkResponseWrapper
import com.airtel.demo.domain.interactors.AddressUseCase
import com.airtel.demo.domain.models.AddressSuggestionData
import com.airtel.demo.presentation.viewmodel.AddressViewModel
import com.nhaarman.mockito_kotlin.mock
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
open class ViewModelTest {

    lateinit var viewModel: AddressViewModel

    @MockK
    lateinit var useCase: AddressUseCase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = TestCoroutineRule()


    var dispatcher= TestCoroutineDispatcher()

    @MockK
    lateinit var loaderObserver: Observer<Boolean>


    @MockK
    lateinit var successObserver: Observer<AddressSuggestionData>

    @MockK
    lateinit var errorObserver: Observer<String>

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = AddressViewModel(useCase, dispatcher, dispatcher)
    }

    @Test
    fun testGetAddressSuggestionSuccess() {
        coroutineRule.runBlockingTest {
        every { loaderObserver.onChanged(any()) } just Runs
        viewModel.loadingState.observeForever(loaderObserver)
        viewModel.addressSuggestions.observeForever(successObserver)
        val addressData = mock<AddressSuggestionData>()
        val successResponse =
                NetworkResponseWrapper.NetworkSuccess(addressData)
        coEvery { useCase.execute(String(), String()) } returns successResponse

            viewModel.getAddressSuggestion(String(), String())
            verify(atLeast = 1) { loaderObserver.onChanged(any()) }
            verify(exactly = 1) { successObserver.onChanged(successResponse.data) }
            verify(atLeast = 1) { loaderObserver.onChanged(any()) }
        }
        viewModel.loadingState.removeObserver(loaderObserver)
        viewModel.addressSuggestions.removeObserver(successObserver)
    }

    @Test
    fun testGetAddressSuggestionFailure() {
        every { loaderObserver.onChanged(any()) } just Runs
        viewModel.loadingState.observeForever(loaderObserver)
        viewModel.addressSuggestionError.observeForever(errorObserver)
        val errorMsg  = "Error"
        val errorResponse =
                NetworkResponseWrapper.NetworkError<AddressSuggestionData>(errorMsg)
        coEvery { useCase.execute(String(), String()) } returns errorResponse


        coroutineRule.runBlockingTest {
            viewModel.getAddressSuggestion(String(), String())
            verify(atLeast = 1) { loaderObserver.onChanged(any()) }
            verify(exactly = 1) { errorObserver.onChanged(errorMsg) }
            verify(atLeast = 1) { loaderObserver.onChanged(any()) }
        }
        viewModel.loadingState.removeObserver(loaderObserver)
        viewModel.addressSuggestionError.removeObserver(errorObserver)
    }

}