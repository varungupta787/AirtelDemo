package com.airtel.demo.presentation.di.modules

import com.airtel.demo.data.repositories.AddressRepoImpl
import com.airtel.demo.domain.repositories.AddressRepo
import com.airtel.demo.presentation.di.scope.ApplicationScope
import com.topgithub.demo.data.network.ApiService
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RepositoryModule {

    @Binds @ApplicationScope
    internal abstract fun getAddressRepository(repo: AddressRepoImpl): AddressRepo

    /*@Provides
    @ApplicationScope
    fun getAddressRepo(networkService: ApiService): AddressRepo {
        return AddressRepoImpl(networkService)
    }*/
}