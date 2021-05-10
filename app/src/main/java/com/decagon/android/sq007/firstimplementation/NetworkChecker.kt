package com.decagon.android.sq007.firstimplementation

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

class NetworkChecker(private val connectivityManager: ConnectivityManager?) {


    inline fun performIfConnectedToInternet(action: () -> Unit) {
        // here we are checking if there is connection, if yes we execute an action
        if (hasInternetConnection()) {
            action()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun hasInternetConnection(): Boolean {
        // this will use the manager to get the information the current network, if there is one it fetches it capabilities to see if it's connected to a wifi network, mobile or a VPN
        // if there isn't a connection it returns false
        val network = connectivityManager?.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        // this checks the type of connection if it is a wifi, mobilr or a vpn
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
    }

   /* private val networkStatusChecker by lazy {
        NetworkStatusChecker(activity?.getSystemService(ConnectivityManager::class.java))
    }
*/

}
