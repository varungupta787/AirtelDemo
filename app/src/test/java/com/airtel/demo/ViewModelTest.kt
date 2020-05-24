package com.airtel.demo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.airtel.demo.domain.interactors.AddressUseCase
import com.airtel.demo.domain.models.AddressSuggestionData
import com.airtel.demo.domain.repositories.AddressRepo
import com.airtel.demo.presentation.di.qualifiers.TrampolineScheduler
import com.airtel.demo.presentation.ui.AutoSuggestionActivity
import com.airtel.demo.presentation.viewmodel.AddressViewModel
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Scheduler
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.reflect.jvm.internal.impl.javax.inject.Inject

open class ViewModelTest {

    lateinit var viewModel: AddressViewModel
    //@InjectMocks
    lateinit var useCase: AddressUseCase

    @TrampolineScheduler
    lateinit var scheduler: Scheduler


    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        //useCase = AddressUseCase(repo,  scheduler, scheduler)
        useCase = mock<AddressUseCase>()
        viewModel = AddressViewModel(useCase)
    }

    @Test
    fun testGetAddressSuggestionSuccess() {

        val addressSuggestionData = mock<AddressSuggestionData>()
        val onSuccessCallback = argumentCaptor<(AddressSuggestionData) -> Unit>()
        val onErrorCallback = argumentCaptor<(String) -> Unit>()

        whenever(useCase.execute("", "",
                onSuccess = onSuccessCallback.capture(), onError = onErrorCallback.capture()))
        doAnswer { onSuccessCallback.firstValue(addressSuggestionData)}

        viewModel.getAddressSuggestion(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
        Assert.assertEquals(true, viewModel.loadingState)
        useCase.execute(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(),
                onSuccessCallback.capture(), ArgumentMatchers.any())
        //onSuccessCallback.firstValue
        Assert.assertEquals(addressSuggestionData , viewModel.addressSuggestions.value)
        Assert.assertEquals(false, viewModel.loadingState)

    }

    @Test
    fun testGetAddressSuggestionFailure() {

    }

}