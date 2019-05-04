package com.bison.share

import android.content.Intent
import android.text.TextUtils
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class SharePlugin : MethodCallHandler {
    companion object {
        const val CHANNEL = "plugins/share"

        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), CHANNEL)
            channel.setMethodCallHandler(SharePlugin())
        }
    }

    override fun onMethodCall(call: MethodCall, result: Result) = when (call.method) {
        "share" -> {
            if (!TextUtils.isEmpty(call.argument<String>("text"))) {
                share("")
                result.success(null)
            } else {
                result.error("", "", "")
            }
        }
        else -> result.notImplemented()
    }

    private fun share(text: String) {
        if (TextUtils.isEmpty(text)) {
            throw  IllegalArgumentException("text cannot be null here.")
        }

        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, "")
            type = "text/plain"
        }
        val chooserIntent = Intent.createChooser(shareIntent, null)
    }
}
