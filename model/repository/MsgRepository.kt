package com.lft.hileia.model.repository

import com.lft.hileia.model.net.BaseEntity
import javax.inject.Inject

class MsgRepository @Inject constructor() {

    //获取群信息
    suspend fun getTestData(): BaseEntity<String> {
        return BaseEntity(200, "测试desc", "{age:18,isvisible:0}")
    }

}
