package com.bison.share

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class SharePlugin(private val activity: Activity) : MethodCallHandler {
    companion object {
        private const val CHANNEL = "plugins/share"

        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val plugin = SharePlugin(registrar.activity())
            val channel = MethodChannel(registrar.messenger(), CHANNEL)
            channel.setMethodCallHandler(plugin)
        }
    }

    override fun onMethodCall(call: MethodCall, result: Result) = when (call.method) {
        "share" -> {
            val text = call.argument<String>("text")
            if (!TextUtils.isEmpty(text)) {
                share(text!!)
                result.success(null)
            } else {
                result.error("Error", "TextEmptyError", "Text is Null or Empty")
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

        activity.startActivity(chooserIntent)
    }
}
