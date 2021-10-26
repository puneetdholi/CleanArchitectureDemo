package com.example.architecturedemo.injection

import android.app.Application
import android.content.Context
import com.example.architecturedemo.utils.UIThread
import com.example.data.BuildConfig
import com.example.data.JobExecutor
import com.example.data.network.RestFactory
import com.example.data.network.interceptor.NetworkInterceptor
import com.example.data.network.interceptor.RedirectInterceptor
import com.example.data.network.remote.user.UserRemoteImpl
import com.example.data.repository.UserDataRepository
import com.example.domain.repository.UserRepository
import com.example.data.network.remote.user.UserRemote
import com.example.data.network.service.UserService
import com.example.domain.executor.PostExecutionThread
import com.example.domain.executor.ThreadExecutor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object AppModule {
    @JvmStatic
    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application

    @JvmStatic
    @Singleton
    @Provides
    fun providePostThreadExecutor(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideUserRemote(userRemoteImpl: UserRemoteImpl): UserRemote {
        return userRemoteImpl
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit): UserService {
        return RestFactory.makeUserService(retrofit)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideUserRepository(userDataRepository: UserDataRepository): UserRepository {
        return userDataRepository
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        return RestFactory.makeRetrofit(okHttpClient = okHttpClient, isDebug = BuildConfig.DEBUG)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideOkHttp(networkInterceptor: NetworkInterceptor,
                      redirectInterceptor: RedirectInterceptor
    ): OkHttpClient {
        return RestFactory.makeOkHttpClient(
            networkInterceptor = networkInterceptor,
            redirectInterceptor = redirectInterceptor)
    }
}