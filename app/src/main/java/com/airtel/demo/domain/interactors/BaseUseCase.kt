package com.airtel.demo.domain.interactors

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.rxkotlin.plusAssign

abstract class BaseUseCase<T> constructor(val workScheduler: Scheduler,
                                          val uiThreadScheduler: Scheduler) {


    val disposables = CompositeDisposable()

    fun dispose() {
        disposables.clear()
    }

    protected fun Single<T>.executeUseCase(onSuccess: (T) -> Unit,
                                           onError: (errorMsg: String) -> Unit) {

        disposables += this
                .subscribeOn(workScheduler)
                .observeOn(uiThreadScheduler)
                .subscribeWith(object : DisposableSingleObserver<T>() {
                    override fun onSuccess(t: T) {
                        onSuccess(t)
                    }

                    override fun onError(e: Throwable) {
                        onError("Server Error. Try Again !!!!")
                    }
                })
    }
}
