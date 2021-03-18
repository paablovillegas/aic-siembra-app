package mx.grupo.tepeyac.mexico.aic.siembra.network

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import mx.grupo.tepeyac.mexico.aic.siembra.network.interceptors.AuthenticationInterceptor
import mx.grupo.tepeyac.mexico.aic.siembra.network.interceptors.TokenInterceptor
import mx.grupo.tepeyac.mexico.aic.siembra.utils.GsonBooleanAdapter
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceGenerator {
    private const val BASE_URL = "http://10.11.0.27:4001/siembra/"
    //private const val BASE_URL = "https://e-sterling-inc.com:11020/siembra/"

    private val interceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.HEADERS
    }
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(25, TimeUnit.SECONDS)
    private val gsonBuilder: Gson = GsonBuilder()
        .registerTypeAdapter(Boolean::class.java, GsonBooleanAdapter())
        //.registerTypeAdapter(Date::class.java, UnixEpochDateTypeAdapter().nullSafe())
        .create()
    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
    private var retrofit = builder.build()

    fun <T> createService(
        context: Context,
        service: Class<T>,
        clientId: String,
        password: String
    ): T {
        if (clientId.isNotEmpty() && password.isNotEmpty()) {
//            httpClient.sslSocketFactory(
//                SSLService.getSslSocketFactory(context),
//                SSLService.trustManager
//            )
            val authToken = Credentials.basic(clientId, password)
            val interceptor = AuthenticationInterceptor(authToken)
            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor)
                builder.client(httpClient.build())
                retrofit = builder.build()
            }
        }
        return retrofit.create(service)
    }

    fun <T> createService(context: Context, service: Class<T>, authToken: String): T {
        if (authToken.isNotEmpty()) {
            val interceptor = TokenInterceptor(authToken)
//            httpClient.sslSocketFactory(
//                SSLService.getSslSocketFactory(context),
//                SSLService.trustManager
//            )
            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor)
                builder.client(httpClient.build())
                retrofit = builder.build()
            }
        }
        return retrofit.create(service)
    }
}