package com.xysl.citypackage.model.net

import com.blankj.utilcode.util.ToastUtils
import com.google.gson.GsonBuilder
import com.hiscene.domain.BuildConfig
import com.hiscene.domain.constants.BaseCodeConstants
import com.hiscene.domain.net.ConvertHelper
import com.hiscene.domain.net.RequestMethod
import com.lft.hileia.model.net.BaseResult
import com.lft.hileia.model.net.FailCallBack
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

/**
 * 网络请求工具
 * 分为对象和数组两种请求方式
 * 返回的结果是对象请调用fetch方法
 * 返回的结果是数组请调用fetchList方法
 */
class NetManager private constructor() {
    var apiService: ApiService
    val gson by lazy { GsonBuilder().serializeNulls().create() }

    companion object {
        const val BASE_URL_DEBUG = "http://192.168.20.16:3000/mock/75/"
        const val BASE_URL_RELEASE = "http://192.168.20.16:3000/mock/75/"
        val instance = NetManager()
        const val TIME_SET = 5000L
        val JSON: MediaType = "application/json; charset=utf-8".toMediaType()
    }

    init {
        val hostUrl = if (BuildConfig.DEBUG) BASE_URL_DEBUG else BASE_URL_RELEASE
        val httpLoggingInterceptor = HttpLoggingInterceptor(LoggingInterceptorLogger())
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(TIME_SET, TimeUnit.MILLISECONDS)
            .writeTimeout(TIME_SET, TimeUnit.MILLISECONDS)
            .connectTimeout(TIME_SET, TimeUnit.MILLISECONDS)
            .addNetworkInterceptor(httpLoggingInterceptor)
            .addInterceptor(HeadInterceptor())
            .addInterceptor(TokenInterceptor())
            .retryOnConnectionFailure(true)
            .build()
        apiService = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(hostUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
    }

//----------------------------------------------------------------------接口----------------------------------------------------------------


    /**
     * json 组装的请求体json string,通过requestfactory产生
     */

    suspend inline fun <reified T> getFetch(pair: Pair<String,String>): BaseResult<T> {
        return fetch<T>(RequestMethod.GET, pair.first, pair.second)
    }

    suspend inline fun <reified T> getFetchList(
        pathName: String,
        json: String
    ): BaseResult<List<T>> {
        return fetchList<T>(RequestMethod.GET, pathName, json)
    }

    suspend inline fun <reified T> postFetch(pathName: String, json: String): BaseResult<T> {
        return fetch<T>(RequestMethod.POST, pathName, json)
    }

    suspend inline fun <reified T> postFetchList(
        pathName: String,
        json: String
    ): BaseResult<List<T>> {
        return fetchList<T>(RequestMethod.POST, pathName, json)
    }

    suspend inline fun <reified T> fetch(
        method: RequestMethod,
        pathName: String,
        json: String,
    ): BaseResult<T> {
        try {
            val body = json.toRequestBody(JSON)
            var baseResult = apiService.postFetch<T>(pathName, body)
            when (method) {
                RequestMethod.GET -> {
                    baseResult = apiService.getFetch<T>(pathName, body)
                }
                RequestMethod.POST -> {
                    baseResult = apiService.postFetch<T>(pathName, body)
                }
                RequestMethod.PUT -> {

                }
                RequestMethod.DELETE -> {

                }
            }
            if (baseResult.data == null || "".equals(baseResult.data)) {
                if (!baseResult.isSuccess()) {
                    proceedError(ResponeThrowable(baseResult.code, baseResult.message))
                }
                baseResult.data = null
            } else {
                val dataJson = gson.toJson(baseResult.data)
                baseResult.data = gson.fromJson(dataJson, T::class.java)
            }
            return baseResult
        } catch (e: Exception) {
            var responeThrowable = ApiException.getResponeThrowable(e)
            proceedError(responeThrowable)
            return BaseResult(responeThrowable.code, responeThrowable.desc, null)
        }
    }

    suspend inline fun <reified T> fetchList(
        method: RequestMethod,
        pathName: String,
        json: String,
    ): BaseResult<List<T>> {
        try {
            val body = json.toRequestBody(JSON)
            var baseResult = apiService.postFetchList<T>(pathName, body)
            when (method) {
                RequestMethod.POST -> {
                    baseResult = apiService.getFetchList<T>(pathName, body)
                }
                RequestMethod.POST -> {
                    baseResult = apiService.postFetchList<T>(pathName, body)
                }
                RequestMethod.PUT -> {

                }
                RequestMethod.DELETE -> {

                }
            }
            if (baseResult.data == null || "[]".equals(baseResult.data) || "".equals(baseResult.data)) {
                if (!baseResult.isSuccess()) {
                    proceedError(ResponeThrowable(baseResult.code, baseResult.message))
                }
                baseResult.data = null
            } else {
                val dataJson = gson.toJson(baseResult.data)
                baseResult.data = ConvertHelper.parseJsonToList<T>(dataJson)
            }
            return baseResult
        } catch (e: Exception) {
            var responeThrowable = ApiException.getResponeThrowable(e)
            proceedError(responeThrowable)
            return BaseResult(responeThrowable.code, responeThrowable.desc, null)
        }
    }


    //网络请求出错都会走这个方法
    fun proceedError(responeThrowable: ResponeThrowable) {
        if (responeThrowable.code == BaseCodeConstants.CODE_TOKEN_EXPIRE) {
            //token失效处理.需要重新登录
            return
        }
    }

}


