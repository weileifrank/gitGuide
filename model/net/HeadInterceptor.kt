package com.xysl.citypackage.model.net

import android.text.TextUtils
import okhttp3.Interceptor
import okhttp3.Response


class HeadInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
//    companion object {
//        var uid = CommonUtil.getAndroidId()
//        var imei = CommonUtil.getImei()
//    }
//
//    override fun intercept(chain: Interceptor.Chain): Response {
//        if (TextUtils.isEmpty(imei)) {
//            imei = CommonUtil.getImei()
//        }
//        val original = chain.request()
//        val request = original.newBuilder()
//            .addHeader("appType", BaseKeyAppIdConstants.AppType)
//            .addHeader("appId", BaseKeyAppIdConstants.XYSL_ID)
//            .addHeader("channel", App.channelName)
//            .addHeader("deviceType", "android")
//            .addHeader("uid", uid)
//            .addHeader("imei", imei)
//            .addHeader("oaid", InitManager.oaid)
//            .addHeader("appVersion", BaseKeyAppIdConstants.XYSL_APP_VERSION)
//            .addHeader("appVersionCode", BaseKeyAppIdConstants.XYSL_APP_VERSION_CODE.toString())
//            .addHeader(BaseNameConstants.SESSIONID, InitManager.session_id)
//            .build()
//        return chain.proceed(request)
//    }

}