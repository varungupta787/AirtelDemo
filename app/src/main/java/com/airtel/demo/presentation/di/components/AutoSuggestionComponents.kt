package com.airtel.demo.presentation.di.components

import com.airtel.demo.presentation.di.modules.NetworkModule
import com.airtel.demo.presentation.di.modules.RepositoryModule
import com.airtel.demo.presentation.di.modules.RxSchedulerModule
import com.airtel.demo.presentation.di.modules.ViewModelModule
import com.airtel.demo.presentation.di.scope.ApplicationScope
import com.airtel.demo.presentation.ui.AutoSuggestionActivity
import dagger.Component


@ApplicationScope
@Component(modules = [NetworkModule::class,
    ViewModelModule::class,
    RxSchedulerModule::class,
    RepositoryModule::class])
interface AutoSuggestionComponents {
    fun injectAutoSuggestionActivity(activity: AutoSuggestionActivity)
}
