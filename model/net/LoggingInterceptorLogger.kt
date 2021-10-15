package com.xysl.citypackage.model.net

import com.lft.libbase.utils.logdHttp
import okhttp3.logging.HttpLoggingInterceptor

class LoggingInterceptorLogger : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        logdHttp("LoggingInterceptorLogger:$message")
    }
}