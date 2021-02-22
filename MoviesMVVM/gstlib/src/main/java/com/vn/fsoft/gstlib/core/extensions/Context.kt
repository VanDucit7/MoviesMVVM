package com.vn.fsoft.gstlib.core.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentActivity
import com.vn.fsoft.gstlib.core.utils.AppConstants


val Context.isInternetAvailable: Boolean
    get() {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (AppConstants.beforeM()) {
            return connectivityManager.activeNetworkInfo?.isConnected!!
        } else {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val networkCapabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
    }

fun Context.browse(url: String, newTask: Boolean = false): Boolean {
    return try {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
            if (newTask) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
        true
    } catch (e: Exception) {
        false
    }
}

@SuppressLint("MissingPermission")
fun Context.makeCall(number: String): Boolean {
    return try {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$number"))
        startActivity(intent)
        true
    } catch (e: Exception) {
        false
    }
}

fun Context.rate(): Boolean =
    browse("market://details?id=$packageName") or browse("http://play.google.com/store/apps/details?id=$packageName")


val Context.screenWidth
    get() = resources.displayMetrics.widthPixels

val Context.screenHeight
    get() = resources.displayMetrics.heightPixels

val Context.isGpsEnabled: Boolean
    get() {
        val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

fun Context.inflateLayout(
    @LayoutRes layoutId: Int,
    parent: ViewGroup? = null,
    attachToRoot: Boolean = false
): View = LayoutInflater.from(this).inflate(layoutId, parent, attachToRoot)

val Context.applicationInForeground: Boolean
    get() {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val services = activityManager.runningAppProcesses
        var isActivityFound = false
        if (!services.isNullOrEmpty() && (services[0].processName.equals(
                packageName,
                ignoreCase = true
            )
                    && services[0].importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND)
        ) {
            isActivityFound = true
        }
        return isActivityFound
    }


fun Context?.isAvailable(): Boolean {
    if (this == null) {
        return false
    } else if (this !is Application) {
        if (this is FragmentActivity) {
            return !this.isDestroyed
        } else if (this is Activity) {
            return !this.isDestroyed
        }
    }
    return true
}