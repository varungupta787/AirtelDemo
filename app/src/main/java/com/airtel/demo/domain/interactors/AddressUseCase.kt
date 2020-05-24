package com.airtel.demo.domain.interactors

import com.airtel.demo.domain.models.AddressSuggestionData
import com.airtel.demo.domain.repositories.AddressRepo
import com.airtel.demo.presentation.di.qualifiers.IOScheduler
import com.airtel.demo.presentation.di.qualifiers.UiThreadScheduler
import io.reactivex.Scheduler
import javax.inject.Inject

open class AddressUseCase @Inject constructor(
        var addressRepo: AddressRepo,
        @IOScheduler workScheduler: Scheduler,
        @UiThreadScheduler uiThreadScheduler: Scheduler
) : BaseUseCase<AddressSuggestionData>(workScheduler, uiThreadScheduler) {

    fun execute(queryString: String, city:String,
                onSuccess: (response: AddressSuggestionData) -> Unit,
                onError: (error: String) -> Unit) {
        addressRepo.getAddressSuggestion(queryString, city).executeUseCase(onSuccess, onError)
    }

}