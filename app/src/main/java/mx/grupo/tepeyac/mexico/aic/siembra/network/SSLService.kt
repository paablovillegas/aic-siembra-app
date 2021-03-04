package mx.grupo.tepeyac.mexico.aic.siembra.network

import android.content.Context
import java.io.IOException
import java.security.*
import java.security.cert.CertificateException
import java.util.*
import javax.net.ssl.*

class SSLService {
    companion object {
        lateinit var trustManager: X509TrustManager
        fun getSslSocketFactory(context: Context): SSLSocketFactory {
            try {
                val inputStream = context.assets.open("siembra.p12")
                val keyStore = KeyStore.getInstance("PKCS12")
                keyStore.load(inputStream, ".Tepeyac1".toCharArray())
                val factory = KeyManagerFactory.getInstance("X509")
                factory.init(keyStore, ".Tepeyac1".toCharArray())
                val keyManagers = factory.keyManagers
                val tmf = TrustManagerFactory.getInstance("X509")
                tmf.init(keyStore)
                val trustManagers = tmf.trustManagers
                if (trustManagers.size != 1 || !(trustManagers[0] is X509TrustManager))
                    throw IllegalStateException(
                        "Unexpected default trust managers: " + Arrays.toString(
                            trustManagers
                        )
                    )
                val hostnameVerifier = HostnameVerifier { hostname, _ ->
                    hostname.compareTo("201.107.4.90") == 0 //The Hostname of your server
                }
                HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier)
                val sslContext = SSLContext.getInstance("TLS")
                sslContext.init(keyManagers, trustManagers, null)
                HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.socketFactory)
                trustManager = trustManagers[0] as X509TrustManager
                return sslContext.socketFactory
            } catch (e: CertificateException) {
                e.printStackTrace()
            } catch (e: KeyManagementException) {
                e.printStackTrace()
            } catch (e: UnrecoverableKeyException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            } catch (e: KeyStoreException) {
                e.printStackTrace()
            }
            throw SSLKeyException("Llave no autorizada")
        }
    }
}