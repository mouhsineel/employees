package com.mel.employee.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.mel.employee.utils.NoInternetExceptions
import okhttp3.Interceptor
import okhttp3.Response

class NetworkIntercepter (
        context : Context
        ) : Interceptor {

    private val applicationContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isInternetAvailable())
            throw NoInternetExceptions("No Internet ")

        return chain.proceed(chain.request())
    }

    private fun isInternetAvailable(): Boolean{
        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return  capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)

    }
}
