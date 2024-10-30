package com.ridebeam.channeltalk

import com.zoyi.channel.plugin.android.ChannelIO
import io.flutter.app.FlutterApplication

class App: FlutterApplication() {
    override fun onCreate() {
        super.onCreate()
        ChannelIO.initialize(this)
    }
}