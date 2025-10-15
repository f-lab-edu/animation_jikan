package com.artem.animationjikan.util.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.jikan.moe/v4/"
    private var retrofit: Retrofit? = null

    private val okHttpClient: OkHttpClient by lazy {
        // 1. HTTP 로깅 인터셉터 생성
        val logging = HttpLoggingInterceptor().apply {
            // body 레벨로 설정하여 Request Header, Body, Response Header, Body를 모두 출력합니다.
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        // 2. 클라이언트 빌더에 인터셉터 추가
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    val client: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}