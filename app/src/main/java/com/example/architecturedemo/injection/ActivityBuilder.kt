package com.example.architecturedemo.injection

import com.example.architecturedemo.DashboardActivity
import com.example.architecturedemo.DashboardFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [DashboardFragmentProvider::class])
    abstract fun bindDashboardActivity(): DashboardActivity
}