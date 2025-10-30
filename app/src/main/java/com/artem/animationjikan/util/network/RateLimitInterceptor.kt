package com.artem.animationjikan.util.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class RateLimitInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response = chain.proceed(request)

        var retryCount = 0
        val MAX_RETRIES = 3

        while (response.code == 429 && retryCount < MAX_RETRIES) {
            response.close()
            retryCount++

            // 서버가 요청하는 대기 시간 (초) 또는 기본값 5초 사용
            val retryAfterSeconds = response.header("Retry-After")?.toLongOrNull() ?: 5L

            try {
                // Thread.sleep()을 사용하여 네트워크 스레드를 블로킹 (코루틴 delay와 다름)
                Thread.sleep(retryAfterSeconds * 1000)
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
                return response
            }

            // 요청 재시도
            response = chain.proceed(request)
        }

        return response
    }

}