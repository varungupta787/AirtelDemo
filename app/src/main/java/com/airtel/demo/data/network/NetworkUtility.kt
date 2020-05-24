package com.airtel.demo.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager


object NetworkUtility {

    fun isNetworkAvailable(context: Context?): Boolean {

        val connectManager:ConnectivityManager? = context?.applicationContext?.getApplicationContext()?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifiManager: WifiManager? = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        val netInfo:NetworkInfo? = connectManager?.activeNetworkInfo
        if (netInfo?.isConnected?: false || wifiManager?.isWifiEnabled?:false) {
            return true
        }
        return false
    }
}
