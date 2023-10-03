package ru.anb.myapplication.features.home.data.events

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import ru.anb.myapplication.features.auth.data.PersistentStore
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private  val persistentStore: PersistentStore) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        val token = runBlocking {
            persistentStore.getToken()
        }
        token?.let {
            requestBuilder.addHeader("Authorization", it)
        }

        return chain.proceed(requestBuilder.build())
    }
}