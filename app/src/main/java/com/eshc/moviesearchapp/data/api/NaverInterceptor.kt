package com.eshc.moviesearchapp.data.api

import com.eshc.moviesearchapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class NaverInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val request = original.newBuilder().apply {
            header("X-Naver-Client-Id", "${BuildConfig.NAVER_CLIENT_ID}")
            header("X-Naver-Client-Secret", "${BuildConfig.NAVER_CLIENT_SECRET}")
            method(original.method, original.body)
        }.build()

        return chain.proceed(request)
    }
}