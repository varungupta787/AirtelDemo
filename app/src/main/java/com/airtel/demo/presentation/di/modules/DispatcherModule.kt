package com.airtel.demo.presentation.di.modules

import com.airtel.demo.presentation.di.qualifiers.IODispatcher
import com.airtel.demo.presentation.di.qualifiers.UiThreadDispatcher
import com.airtel.demo.presentation.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
open class DispatcherModule {

    @Provides
    @ApplicationScope
    @IODispatcher
    open fun provideAndroidScheduler(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @ApplicationScope
    @UiThreadDispatcher
    open fun provideIoScheduler(): CoroutineDispatcher = Dispatchers.Main

}