package com.artem.animationjikan.util.network

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.retry
import retrofit2.HttpException

fun <T> Flow<T>.retryOnRateLimit(
    retries: Long = 3,
    initialDelaySeconds: Long = 5
): Flow<T> = retry(retries) { cause ->
    if (cause is HttpException && cause.code() == 429) {
        delay(initialDelaySeconds * 1000)
        return@retry true
    }
    return@retry false
}