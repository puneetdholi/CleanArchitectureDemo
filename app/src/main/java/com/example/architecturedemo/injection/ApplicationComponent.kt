package com.example.architecturedemo.injection

import android.app.Application
import com.example.architecturedemo.ArchitectureApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class),(ActivityBuilder::class), (AndroidSupportInjectionModule::class)])
interface ApplicationComponent : AndroidInjector<ArchitectureApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}