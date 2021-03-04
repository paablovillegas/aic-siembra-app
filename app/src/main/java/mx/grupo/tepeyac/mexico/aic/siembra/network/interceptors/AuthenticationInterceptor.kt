package mx.grupo.tepeyac.mexico.aic.siembra.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor(private val authToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
            .header("Authorization", authToken)
            .header("Content-Type", "application/x-www-form-urlencoded")
        val request = builder.build()
        return chain.proceed(request)
    }
}