package com.example.architecturedemo

import com.example.architecturedemo.views.home.HomeFragment
import com.example.architecturedemo.views.home.HomeFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DashboardFragmentProvider {
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun providesHomeFragment(): HomeFragment
}