package com.xysl.citypackage.model.net

import android.net.ParseException
import com.google.gson.JsonParseException
import com.lft.hileia.R
import com.lft.hileia.model.net.BaseEntity

import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException
import javax.net.ssl.SSLHandshakeException


data class ResponeThrowable(var code: Int, var desc: String, var throwable: Throwable? = null)

//需要约定的异常
object ERROR {
    /**
     * 未知错误
     */
    val UNKNOWN = 1000
    /**
     * 解析错误
     */
    val PARSE_ERROR = 1001
    /**
     * 网络错误
     */
    val NETWORD_ERROR = 1002
    /**
     * 协议出错
     */
    val HTTP_ERROR = 1003
    /**
     * 证书出错
     */
    val SSL_ERROR = 1005

    /**
     * 网络不可用
     */
    val NETWORK_IS_NOT_ARRIVE = 1006

    /**
     * 返回的数据格式异常
     */
    val DATA_FORMAT_WRONG = 1007
    /**
     * 请求已取消
     */
    val REQUEST_CANCEL = 1008
}


object ApiException {
    val UNAUTHORIZED = 401;
    val FORBIDDEN = 403;
    val NOT_FOUND = 404;
    val REQUEST_TIMEOUT = 408;
    val INTERNAL_SERVER_ERROR = 500;
    val BAD_GATEWAY = 502;
    val SERVICE_UNAVAILABLE = 503;
    val GATEWAY_TIMEOUT = 504;

    fun getResponeThrowable(e: Throwable): ResponeThrowable {
//        var ex: ResponeThrowable? = null
//        if (e is HttpException) {
//            var errorString = e.response()?.errorBody()?.string()
//            var baseResponse = GsonUtil.parseJsonToBean(errorString, BaseEntity::class.java)
//            when (e.code()) {
//                UNAUTHORIZED->ex = ResponeThrowable(e.code(), baseResponse?.desc?:e.message())
//                FORBIDDEN, NOT_FOUND, REQUEST_TIMEOUT, GATEWAY_TIMEOUT, INTERNAL_SERVER_ERROR, BAD_GATEWAY, SERVICE_UNAVAILABLE -> ex =
//                    ResponeThrowable(e.code(), baseResponse?.desc?:e.message())
//                else -> ex = ResponeThrowable(e.code(), baseResponse?.desc?:e.message())
//            }
//            return ex
//        } else if (e is JsonParseException || e is JSONException || e is ParseException) {
//            ex = ResponeThrowable(ERROR.PARSE_ERROR, App.instance.getString(R.string.parse_error))
//            return ex
//        } else if (e is ConnectException || e is SocketTimeoutException || e is UnknownHostException) {
//            ex = ResponeThrowable(ERROR.NETWORD_ERROR, App.instance.getString(R.string.connection_failed))
//            return ex
//        } else if (e is SSLHandshakeException) {
//            ex = ResponeThrowable(ERROR.SSL_ERROR, App.instance.getString(R.string.certificat_verification_failed))
//            return ex
//        } else if (e is CancellationException) {
//            ex = ResponeThrowable(ERROR.REQUEST_CANCEL, App.instance.getString(R.string.request_cancel))
//            return ex
//        } else {
//            ex = ResponeThrowable(ERROR.UNKNOWN, App.instance.getString(R.string.unknown_error))
//            return ex
//        }

        return  ResponeThrowable(ERROR.UNKNOWN, "unknown")
    }
}