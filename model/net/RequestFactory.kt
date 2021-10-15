package com.xysl.citypackage.model.net

import android.os.Build
import android.text.TextUtils
import com.blankj.utilcode.util.DeviceUtils
import com.google.gson.Gson
import kotlin.collections.HashMap

/**
 * 用于构建json请求体的工程类
 */
object RequestFactory {
    const val EMPTY_STRING = ""
    val gson by lazy { Gson() }

    //匿名登陆
    fun login(androidId: String): String {
        val methodName = "apptools.clean.center.api.user.userservice.anonymousregister"
        val map = HashMap<String, String>()
        map.put("androidId", androidId)
        return buildJson(methodName, map)
    }

    //发送验证码
    fun requestVerifyCode(mobilePhone: String, captchaCode: String = ""): String {
        val methodName = "apptools.clean.center.api.user.userservice.sendverifycode"
        val map = HashMap<String, String>()
        map.put("mobilePhone", mobilePhone)
        if (!TextUtils.isEmpty(captchaCode)) {
            map.put("captchaCode", captchaCode)
        }
        return buildJson(methodName, map)
    }

    //绑定手机
    fun bindMobile(
        mobilePhone: String,
        smsCode: String,
        captchaCode: String,
        msgId: String
    ): String {
        val methodName = "apptools.clean.center.api.user.userservice.bindmobile"
        val map = HashMap<String, String>()
        map.put("mobilePhone", mobilePhone)
        map.put("smsCode", smsCode)
        map.put("captchaCode", captchaCode)
        map.put("msgId", msgId)
        return buildJson(methodName, map)
    }

    //用户反馈
    fun userFeedBack(
        email: String,
        content: String
    ): String {
        val methodName = "apptools.clean.center.api.usercenterservice.feedback"
        val map = HashMap<String, String>()
        map.put("email", email)
        map.put("content", content)
        return buildJson(methodName, map)
    }

    //绑定微信
    fun doRegister(
        code: String,
        status: String,
        androidId: String
    ): String {
        val methodName = "apptools.clean.center.api.user.userservice.doregister"
        val map = HashMap<String, String>()
        map.put("code", code)
        map.put("status", status)
        map.put("androidId", androidId)
        return buildJson(methodName, map)
    }


    //全局配置
    fun getGlobleConfig(): String {
        val methodName = "apptools.clean.center.api.user.userservice.getuserbasedata"
        return buildJson(methodName, EMPTY_STRING)
    }

    //更新配置
    fun getUpgradeConfig(): String {
        val methodName = "apptools.clean.center.api.appupgradeservice.upgrade"
        return buildJson(methodName, EMPTY_STRING)
    }


//===============================================新加的接口===============================================

    //答题
    fun getQuestions(): String {
        val methodName = "apptools.clean.center.api.walk.answerservice.getquestions"
        return buildJson(methodName, EMPTY_STRING)
    }

    //答题奖励金币数
    fun getQuestionsCoin(rightCount: Int): String {
        val methodName = "apptools.clean.center.api.walk.answerservice.getestimatedincome"
        return buildJson(methodName, rightCount)
    }

    //答题奖励
    fun getAnswerAward(rightCount: Int, coutinueCount: Int, isDouble: Int): String {
        val methodName = "apptools.clean.center.api.walk.answerservice.answerrewardv3"
        val map = HashMap<String, Int>()
        map.put("rightCount", rightCount)
        map.put("coutinueCount", coutinueCount)
        map.put("isDouble", isDouble)
        return buildJson(methodName, map)
    }

    //个人中心
    fun center(): String {
        val methodName = "apptools.clean.center.api.wifi.withdrawservice.getnewwithdrawcenterdata"
        return buildJson(methodName, EMPTY_STRING)
    }

    //提现根据id  withdrawCooldownSecond
    fun withDraw(
        id: String,
        withdrawType: String = "weixin"
    ): String {
        val methodName = "apptools.clean.center.api.wifi.withdrawservice.withdrawbyid"
        val map = HashMap<String, String>()
        map.put("id", id)
        map.put("withdrawType", withdrawType)
        return buildJson(methodName, map)
    }

    //用户提现明细
    fun getWithdrawDetail(): String {
        val methodName = "apptools.clean.center.api.userwithdrawservice.getwithdrawdetail"
        return buildJson(methodName, EMPTY_STRING)
    }

    //获取群信息
    fun getGroupsConfig(): String {
        val methodName = "apptools.clean.center.api.talk.talkwechatservice.getgroupsconfig"
        return buildJson(methodName, EMPTY_STRING)
    }

    //获取欢迎语
    fun getFirstTalks(groupId: Int): String {
        val methodName = "apptools.clean.center.api.talk.talkwechatservice.getfirsttalks"
        return buildJson(methodName, groupId)
    }

    //获取群聊天信息
    fun getTalks(groupId: Int, talkId: Int): String {
        val methodName = "apptools.clean.center.api.talk.talkwechatservice.getalltalksbytalkid"
        val map = HashMap<String, Int>()
        map.put("groupId", groupId)
        map.put("talkId", talkId)
        return buildJson(methodName, map)
    }

    //拆红包
    fun openRedpackage(type: String): String {
        val methodName = "apptools.clean.center.api.talk.talkwechatservice.openredpackage"
        return buildJson(methodName, type)
    }

    //拆红包
    fun openRainRedpackage(openCount: Int, doubleRequest: Int): String {
        val methodName = "apptools.clean.center.api.talk.talkwechatservice.openrainredpackage"
        val map = HashMap<String, String>()
        map.put("openCount", openCount.toString())
        map.put("doubleRequest", doubleRequest.toString())
        return buildJson(methodName, map)
    }

    //红包雨
    fun getPkgRain(): String {
        val methodName = "apptools.clean.center.api.talk.talkwechatservice.getgameconfig"
        return buildJson(methodName, EMPTY_STRING)
    }

    //banner广告
    fun getBanner(param: String = EMPTY_STRING): String {
        val methodName = "apptools.clean.center.api.user.userservice.getappbanner"
        return buildJson(methodName, param)
    }

    //任务获取
    fun taskGet(questionType: String): String {
        val methodName = "apptools.clean.center.api.usercenterservice.usercompletequestion"
        return buildJson(methodName, questionType)
    }

    //积分榜
    fun getDayRanks(): String {
        val methodName = "apptools.clean.center.api.walk.answerservice.getdayranks"
        return buildJson(methodName, EMPTY_STRING)
    }

    //称号榜
    fun getAllRanks(): String {
        val methodName = "apptools.clean.center.api.walk.answerservice.getallranks"
        return buildJson(methodName, EMPTY_STRING)
    }

    //上期排名
    fun getLastRanks(): String {
        val methodName = "apptools.clean.center.api.walk.answerservice.getyestdayranks"
        return buildJson(methodName, EMPTY_STRING)
    }

    //邀请好友记录
    fun getInvitationUsers(): String {
        val methodName = "apptools.clean.center.api.walk.walkinvitationservice.getinvitationusers"
        return buildJson(methodName, EMPTY_STRING)
    }

    //额外奖励
    fun getAnswerExtraAward(): String {
        val methodName = "apptools.clean.center.api.walk.answerservice.answerextreward"
        return buildJson(methodName, EMPTY_STRING)
    }

    //签到数据
    fun giveCheckIn(): String {
        val methodName = "apptools.clean.center.api.checkincenterservice.getcheckinmainpage"
        return buildJson(methodName, EMPTY_STRING)
    }

    //签到
    fun signIn(): String {
        val methodName = "apptools.clean.center.api.checkincenterservice.checkin"
        return buildJson(methodName, EMPTY_STRING)
    }

    //跑马灯
    fun getMarquee(): String {
        val methodName = "apptools.clean.center.api.walk.answerservice.getallmessages"
        return buildJson(methodName, EMPTY_STRING)
    }

    //通知记录
    fun getRankRewardRecord(): String {
        val methodName = "apptools.clean.center.api.walk.answerservice.getanswerrankrewardrecord"
        return buildJson(methodName, EMPTY_STRING)
    }

    //排名奖励
    fun getRankReward(day: String): String {
        val methodName = "apptools.clean.center.api.walk.answerservice.getrankreward"
        return buildJson(methodName, day)
    }

    fun <T> buildJson(methodName: String, obj: T): String {
        val buildMap = HashMap<String, Any>()
        var list = ArrayList<T>()
        list.add(obj)
        buildMap.put("methodName", methodName)
        buildMap.put("paramValues", list)
        return gson.toJson(buildMap)
    }

}