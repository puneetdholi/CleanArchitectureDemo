package com.example.architecturedemo.views.home

import androidx.lifecycle.ViewModelProvider
import com.example.architecturedemo.utils.ViewModelProviderFactory
import com.example.domain.interactor.GetAllUsersUseCase
import dagger.Module
import dagger.Provides

@Module
object HomeFragmentModule {
    @JvmStatic
    @Provides
    fun homeViewModel(getAllUsersUseCase: GetAllUsersUseCase): HomeViewModel {
        return HomeViewModel(getAllUsersUseCase)
    }

    @JvmStatic
    @Provides
    fun providesHomeViewModel(homeViewModel: HomeViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(homeViewModel)
    }
}