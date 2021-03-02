package com.airtel.demo.presentation.di.components

import com.airtel.demo.presentation.di.modules.*
import com.airtel.demo.presentation.di.scope.ApplicationScope
import com.airtel.demo.presentation.ui.AutoSuggestionActivity
import dagger.Component


@ApplicationScope
@Component(modules = [NetworkModule::class,
    ViewModelModule::class,
    DispatcherModule::class,
    RepositoryModule::class])
interface AutoSuggestionComponents {
    fun injectAutoSuggestionActivity(activity: AutoSuggestionActivity)
}
