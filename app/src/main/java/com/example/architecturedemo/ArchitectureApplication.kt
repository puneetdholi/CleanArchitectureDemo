package com.example.architecturedemo

import com.example.architecturedemo.injection.DaggerApplicationComponent
import dagger.android.DaggerApplication

class ArchitectureApplication : DaggerApplication() {
    override fun applicationInjector() =
        DaggerApplicationComponent.builder().application(this).build()

}