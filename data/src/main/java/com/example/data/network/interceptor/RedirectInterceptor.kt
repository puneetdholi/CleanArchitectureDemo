package com.example.data.network.interceptor

import android.content.Context
import com.example.data.R
import com.example.domain.error.DomainError
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RedirectInterceptor @Inject constructor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val priorResponse = response.priorResponse
        if (priorResponse != null) {
            if (priorResponse.isRedirect) {
                throw DomainError.NoInternetException(context.getString(R.string.string_no_internet))
            }
        }
        return response
    }
}