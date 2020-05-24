package com.airtel.demo.presentation.di.modules

import com.airtel.demo.presentation.di.qualifiers.IOScheduler
import com.airtel.demo.presentation.di.qualifiers.UiThreadScheduler
import com.airtel.demo.presentation.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
open class RxSchedulerModule {

    @Provides
    @ApplicationScope
    @UiThreadScheduler
    open fun provideAndroidScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @ApplicationScope
    @IOScheduler
    open fun provideIoScheduler(): Scheduler {
        return Schedulers.io()
    }
}