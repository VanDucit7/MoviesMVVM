package com.vn.fsoft.gstlib.core.utils

import android.os.Build

object AppConstants {

    const val STRING_EMPTY: String = ""
    const val TIME_OUT_SECONDS = 15

    fun fromKitKat() = fromSpecificVersion(Build.VERSION_CODES.KITKAT)
    fun fromM() = fromSpecificVersion(Build.VERSION_CODES.M)
    fun beforeM() = beforeSpecificVersion(Build.VERSION_CODES.M)
    fun fromN() = fromSpecificVersion(Build.VERSION_CODES.N)
    fun beforeN() = beforeSpecificVersion(Build.VERSION_CODES.N)
    fun fromO() = fromSpecificVersion(Build.VERSION_CODES.O)
    fun beforeO() = beforeSpecificVersion(Build.VERSION_CODES.O)
    fun fromP() = fromSpecificVersion(Build.VERSION_CODES.P)
    fun beforeP() = beforeSpecificVersion(Build.VERSION_CODES.P)
    fun fromQ() = fromSpecificVersion(Build.VERSION_CODES.Q)
    fun beforeQ() = beforeSpecificVersion(Build.VERSION_CODES.Q)
    fun fromR() = fromSpecificVersion(Build.VERSION_CODES.R)
    fun beforeR() = beforeSpecificVersion(Build.VERSION_CODES.R)

    private fun fromSpecificVersion(version: Int): Boolean = Build.VERSION.SDK_INT >= version
    private fun beforeSpecificVersion(version: Int): Boolean = Build.VERSION.SDK_INT < version
}