package com.airtel.demo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airtel.demo.domain.interactors.AddressUseCase
import com.airtel.demo.domain.models.AddressSuggestionData
import javax.inject.Inject


open class AddressViewModel
@Inject constructor(private var addressUsecase: AddressUseCase) : ViewModel() {
    private var suggestionData = MutableLiveData<AddressSuggestionData>()
    private var suggestionError = MutableLiveData<String>()
    private var showLoader = MutableLiveData<Boolean>()

    fun getAddressSuggestion(queryString: String, city:String) {
        showLoader.value = true
        addressUsecase.execute(queryString, city, this::onSuccess, this::onError)
    }

    private fun onSuccess(addressSuggestion: AddressSuggestionData){
        suggestionData.value = addressSuggestion
        showLoader.value = false
    }

    private fun onError(errorMsg: String) {
        suggestionError.value = errorMsg
        showLoader.value = false
    }

    val addressSuggestions: LiveData<AddressSuggestionData>
        get() = suggestionData

    val addressSuggestionError: LiveData<String>
        get() = suggestionError

    val loadingState: LiveData<Boolean>
        get() = showLoader

    override fun onCleared() {
        addressUsecase.dispose()
        super.onCleared()
    }

}
