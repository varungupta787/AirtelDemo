package com.airtel.demo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airtel.demo.data.network.NetworkResponseWrapper
import com.airtel.demo.domain.interactors.AddressUseCase
import com.airtel.demo.domain.models.AddressSuggestionData
import com.airtel.demo.presentation.di.qualifiers.IODispatcher
import com.airtel.demo.presentation.di.qualifiers.UiThreadDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject


open class AddressViewModel
@Inject constructor(private var addressUsecase: AddressUseCase,
                    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
                    @UiThreadDispatcher private val mainDispatcher: CoroutineDispatcher) : ViewModel() {
    private var suggestionData = MutableLiveData<AddressSuggestionData>()
    private var suggestionError = MutableLiveData<String>()
    private var showLoader = MutableLiveData<Boolean>()

    fun getAddressSuggestion(queryString: String, city: String) {
        showLoader.value = true
        viewModelScope.launch(ioDispatcher) {
            val networkData: NetworkResponseWrapper<AddressSuggestionData> =
                    addressUsecase.execute(queryString, city)
            launch(mainDispatcher) {
                when (networkData) {

                    is NetworkResponseWrapper.NetworkSuccess -> {
                        suggestionData.value = networkData.data as AddressSuggestionData
                    }
                    is NetworkResponseWrapper.NetworkError -> {
                        suggestionError.value = networkData.errorMsg
                    }
                }
                showLoader.value = false
            }
        }
    }

    val addressSuggestions: LiveData<AddressSuggestionData>
        get() = suggestionData

    val addressSuggestionError: LiveData<String>
        get() = suggestionError

    val loadingState: LiveData<Boolean>
        get() = showLoader

}
