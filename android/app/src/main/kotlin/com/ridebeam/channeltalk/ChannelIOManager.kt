package com.ridebeam.channeltalk

import BootConfig
import android.app.Activity
import com.zoyi.channel.plugin.android.ChannelIO
import com.zoyi.channel.plugin.android.open.enumerate.BootStatus
import com.zoyi.channel.plugin.android.open.listener.ChannelPluginListener
import com.zoyi.channel.plugin.android.open.model.PopupData
import com.zoyi.channel.plugin.android.open.model.User
import io.channel.com.google.gson.Gson
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodChannel

class ChannelIOManager(private val activity: Activity) : ChannelPluginListener {
    private var eventSink: EventChannel.EventSink? = null


    override fun onShowMessenger() {
        ChannelIO.showMessenger(activity)
    }

    override fun onHideMessenger() {
        ChannelIO.hideMessenger()
    }

    override fun onChatCreated(p0: String?) {
        TODO("Not yet implemented")
    }

    override fun onBadgeChanged(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun onBadgeChanged(p0: Int, p1: Int) {
        TODO("Not yet implemented")
    }

    override fun onFollowUpChanged(p0: MutableMap<String, String>?) {
        TODO("Not yet implemented")
    }

    override fun onUrlClicked(p0: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onPushNotificationClicked(p0: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onPopupDataReceived(p0: PopupData?) {
        TODO("Not yet implemented")
    }

    companion object {
        const val CHANNEL_CHANNELIO = "com.ridebeam.channeltalk/channelIO"
        const val CHANNEL_BADGE_EVENT = "com.ridebeam.channeltalk/channelIO/badge"
    }
}