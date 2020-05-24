package com.airtel.demo.presentation.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.airtel.demo.presentation.di.scope.ApplicationScope
import com.airtel.demo.presentation.viewmodel.AddressViewModel
import com.airtel.demo.presentation.viewmodel.ViewModelsFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelsFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AddressViewModel::class)
    internal abstract fun getGithubRepoViewModel(viewModel: AddressViewModel): ViewModel
}