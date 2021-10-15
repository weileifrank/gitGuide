package com.xysl.citypackage.model.net

import com.lft.hileia.model.net.BaseEntity
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("gateway.action")
    suspend fun <T> fetch(
        @Body requestBody: RequestBody,
        @Header(NetManager.NEED_AUTH_HEADER_KEY) isNeedAuth: String
    ): BaseEntity<T>

    //同步方法,用的很少
    @POST("gateway.action")
    fun fetchSync(
        @Body requestBody: RequestBody,
        @Header(NetManager.NEED_AUTH_HEADER_KEY) isNeedAuth: String
    ): Call<ResponseBody>

    @POST("gateway.action")
    suspend fun <T> fetchList(
        @Body requestBody: RequestBody,
        @Header(NetManager.NEED_AUTH_HEADER_KEY) isNeedAuth: String
    ): BaseEntity<List<T>>

    //主要用于数据上报
    @POST("gateway.action")
    suspend fun upload(@Body requestBody: RequestBody): ResponseBody

}