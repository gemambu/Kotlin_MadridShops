package com.gmb.madridshops.repository.thread

import android.os.Handler
import android.os.Looper

fun DispatchOnMainThread(codeToRun: Runnable) {
    val uiHandler = Handler(Looper.getMainLooper())
    uiHandler.post(codeToRun)
}