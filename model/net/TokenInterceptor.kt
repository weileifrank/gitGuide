package com.xysl.citypackage.model.net

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response


class TokenInterceptor : Interceptor {
    val gson by lazy { Gson() }
    override fun intercept(chain: Interceptor.Chain): Response {
//        var header = chain.request().header(NetManager.NEED_AUTH_HEADER_KEY)
//        if (!NetManager.NEED_AUTH_HEADER_VALUE.equals(header)) {
//            logdTest("不需要验证 header=$header  thread name=${Thread.currentThread().name}")
//            return chain.proceed(chain.request())
//        }
//        if (TextUtils.isEmpty(InitManager.session_id)) {
//            synchronized(TokenInterceptor::class.java) {
//                if (TextUtils.isEmpty(InitManager.session_id)) {
//                    var json = RequestFactory.login(CommonUtil.getAndroidId())
//                    try {
//                        val json =
//                            NetManager.instance.fetchSync(json, false).execute().body()?.string()
//                        json?.apply {
//                            var temp = gson.fromJson(this, BaseEntity::class.java)
//                            temp?.data?.apply {
//                                val account = gson.fromJson(gson.toJson(this), Account::class.java)
//                                if (!TextUtils.isEmpty(account.sessionId)) {
//                                    InitManager.updateAccount(account)
//                                }
//                            }
//                        }
//                    } catch (e: Exception) {
//                        logDh("tokenhead exception=${e.message}")
//                    }
//                }
//            }
//        }
//        val request = chain.request().newBuilder()
//            .header(BaseNameConstants.SESSIONID, InitManager.session_id)
//            .build()
        return chain.proceed(chain.request())
    }

}