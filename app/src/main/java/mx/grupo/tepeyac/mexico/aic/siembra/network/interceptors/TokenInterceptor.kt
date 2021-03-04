package mx.grupo.tepeyac.mexico.aic.siembra.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
            .header("Content-Type", "application/json;charset=utf-8")
            .header("x-token", token)
        val request = builder.build()
        return chain.proceed(request)
    }
}