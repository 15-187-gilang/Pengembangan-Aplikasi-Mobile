package com.example.tugas3.platform

import android.os.Build

class AndroidDeviceInfo : DeviceInfo {
    override val model: String = Build.MODEL
    override val manufacturer: String = Build.MANUFACTURER
    override val androidVersion: String = Build.VERSION.RELEASE
    override val sdkVersion: Int = Build.VERSION.SDK_INT
}
