package com.example.bionetsample.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.core.content.ContextCompat

fun Context.isOnline(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo ?: null
    val isWifiConnected = (networkInfo != null && networkInfo.isConnected &&
            networkInfo.type == ConnectivityManager.TYPE_WIFI)
    val isMobileConnected = (networkInfo != null && networkInfo.isConnected &&
            networkInfo.type == ConnectivityManager.TYPE_MOBILE)

    return isWifiConnected || isMobileConnected
}