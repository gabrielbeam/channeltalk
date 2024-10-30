package com.ridebeam.channeltalk

import BootConfig
import BootResult
import ChannelIOApi
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.leanplum.Leanplum
import com.leanplum.LeanplumActivityHelper
import com.leanplum.annotations.Parser
import com.ridebeam.channeltalk.LeanplumMessage
import com.zoyi.channel.plugin.android.ChannelIO
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BinaryMessenger

class MainActivity: FlutterActivity(), ChannelIOApi {

    companion object {
        const val TAG = "MainActivity"
        var mLeanplumApi: LeanplumMessage.BridgeLeanplumMsgCallbacks? = null
    }
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        ChannelIOApi.setUp(flutterEngine.dartExecutor.binaryMessenger, this)
        val binaryMessenger: BinaryMessenger = flutterEngine.dartExecutor.binaryMessenger
        LeanplumMessage.BridgeLeanplumNativeMethod.setup(
            binaryMessenger,
            BridgeLeanplumNativeMethodImpl(this, this.application)
        )
        mLeanplumApi = LeanplumMessage.BridgeLeanplumMsgCallbacks(binaryMessenger)


    }

    override fun boot(config: BootConfig): BootResult {
        TODO("Not yet implemented")
    }

    override fun sleep() {
        ChannelIO.sleep()
    }

    override fun shutdown() {
        ChannelIO.shutdown()
    }

    override fun showChannelButton() {
        ChannelIO.showChannelButton()
    }

    override fun showMessenger() {
        ChannelIO.showMessenger(this)
    }

    override fun track(eventName: String) {
        ChannelIO.track(eventName)
    }

    private class BridgeLeanplumNativeMethodImpl
    constructor(
        private val context: Context,
        private val application: Application,
    ) : LeanplumMessage.BridgeLeanplumNativeMethod {
        override fun init(appId: String, appKey: String) {
            Log.d(TAG, "BridgeLeanplumNativeMethodImpl init, appId $appId, appKey $appKey")

            Leanplum.setApplicationContext(context)
            Parser.parseVariables(this)

            //  For session lifecycle tracking. Must be called if you do not extend
            // LeanplumApplication Class
            LeanplumActivityHelper.enableLifecycleCallbacks(application)

            Leanplum.setAppIdForDevelopmentMode(appId, appKey)

            // This will only run once per session, even if the activity is restarted.
            Leanplum.start(context)
            Leanplum.track("LEANPLUM_START_ANDROID")
        }

        override fun setUserAttributes(attributes: LeanplumMessage.BridgeLeanplumUserAttributes) {
            Log.d(
                TAG,
                "BridgeLeanplumNativeMethodImpl setUserAttributes, userId ${attributes.userId}, email ${attributes.email}"
            )
            Leanplum.setUserId(attributes.userId)
            Leanplum.track("LEANPLUM_SET_USER_ID", mutableMapOf("userId" to attributes.userId))

            val attributeMap: MutableMap<String, Any> = mutableMapOf<String, Any>()
            attributeMap.put("email", attributes.email ?: "")
            Leanplum.setUserAttributes(attributeMap)
            Leanplum.track("LEANPLUM_SET_EMAIL", attributeMap)
        }

        override fun track(eventName: String, params: MutableMap<String?, Any?>?) {
            Log.d(TAG, "BridgeLeanplumNativeMethodImpl track, event $eventName ")
            Leanplum.track(eventName, params)
        }
    }
}


