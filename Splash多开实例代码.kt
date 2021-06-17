package com.xysl.watermelonclean.ad

import android.app.Activity
import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.bytedance.sdk.openadsdk.AdSlot
import com.qq.e.ads.splash.SplashAD
import com.xysl.common.base.utils.LogUtil
import com.xysl.watermelonclean.MyApp
import com.xysl.watermelonclean.ad.config.TTAdManagerHolder
import com.xysl.watermelonclean.ad.csj.SplashAdListenerAdapter
import com.xysl.watermelonclean.ad.gdt.GdtSplashADListenerAdapter
import com.xysl.watermelonclean.bean.AdPositonType
import com.xysl.watermelonclean.bean.AdvertisingData
import com.xysl.watermelonclean.utils.AdLogTag
import com.xysl.watermelonclean.utils.BaseTimeConstants
import com.xysl.watermelonclean.utils.RxjavaUtil
import com.xysl.watermelonclean.utils.ThreadUtil
import io.reactivex.disposables.Disposable
import kotlin.concurrent.thread

class CacheScene(var type: String = "", var obj: Any? = null)
class SplashAdManager private constructor() {

    companion object {
        const val TAG = "SplashAdManager"
        val instance = SplashAdManager()
        const val DELAY = 2000L
    }

    val list by lazy { mutableListOf<AdvertisingData>() }

    //闪屏
    fun openSplashSameTime(
        activity: Activity?,
        container: ViewGroup?,
        callback: (Int, Boolean) -> Unit = { splashAdSize, isLoadError -> }
    ) {
        list.clear()
        val adPositonConfigBean1 =
            AdManager.getAdPositonConfigBeanByType(AdPositonType.AD_SPLASH_COLD)
        adPositonConfigBean1?.apply {
            list.addAll(advertisingDataList)
        }
        val adPositonConfigBean2 =
            AdManager.getAdPositonConfigBeanByType(AdPositonType.AD_SPLASH_COLD2)
        adPositonConfigBean2?.apply {
            list.addAll(advertisingDataList)
        }

        if (list.size == 0) {
            callback?.invoke(0, false)
            return
        }
        var isExposure = false
        container?.removeAllViews()
        var cacheScene1 = CacheScene()
        var cacheScene2 = CacheScene()
//        var type1 = ""
//        var type2 = ""
//        if (list.size > 0) {
//            type1 = list[0].advertisingType
//        }
//        if (list.size > 1) {
//            type2 = list[1].advertisingType
//        }
        list?.apply {
            RxjavaUtil.countIntervalTimeMill(this.size, 0)?.subscribe {
                val advertisingData = this[it]
                if (AdManager.CSJ == advertisingData.advertisingType) {
                    val adSlot = AdSlot.Builder().setCodeId(advertisingData.adsenseId)
                        .setImageAcceptedSize(1080, 1920).build()
                    val listener =
                        SplashAdListenerAdapter(
                            list.size,
                            advertisingData,
                            activity,
                            container,
                            callback
                        ) {
                            LogUtil.d("${AdLogTag.SPLASH_TAG} csj 加载成功")
                            cacheScene1.type = advertisingData.advertisingType
                            cacheScene1.obj = it
                            container?.apply {
                                showAdView(this, cacheScene1)
                            }
                        }
                    listener.exposureCallBack = {
                        LogUtil.d("${AdLogTag.SPLASH_TAG} csj 曝光成功")
                        if (!isExposure) {
                            isExposure = true
                            if (cacheScene2.obj != null) {
                                container?.let { showAdView(it, cacheScene2) }
                            }
                        }
                    }
                    TTAdManagerHolder.get().createAdNative(MyApp.app)
                        .loadSplashAd(adSlot, listener, BaseTimeConstants.AD_TIME_OUT)
                } else if (AdManager.GDT == advertisingData.advertisingType) {
                    var gdtSplashAD: SplashAD? = null
                    val listener =
                        GdtSplashADListenerAdapter(
                            list.size,
                            advertisingData,
                            container,
                            callback
                        ) {
                            LogUtil.d("${AdLogTag.SPLASH_TAG} gdt 加载成功")
                            cacheScene1.type = advertisingData.advertisingType
                            cacheScene1.obj = gdtSplashAD
                            container?.apply {
                                showAdView(this, cacheScene1)
                            }
                        }
                    listener.exposureCallBack = {
                        LogUtil.d("${AdLogTag.SPLASH_TAG} gdt 曝光成功")
                        if (!isExposure) {
                            isExposure = true
                            if (cacheScene2.obj != null) {
                                container?.let { showAdView(it, cacheScene2) }
                            }
                        }
                    }
                    gdtSplashAD = SplashAD(
                        activity,
                        advertisingData.adsenseId,
                        listener,
                        BaseTimeConstants.AD_TIME_OUT
                    )

                    gdtSplashAD?.fetchAdOnly()
                }
            }
        }
    }

    private fun showAdView(
        viewGroup: ViewGroup,
        cacheScene: CacheScene
    ) {
        cacheScene?.apply {
            if (viewGroup.childCount == 0) {
                synchronized(SplashAdManager::class.java) {
                    if (viewGroup.childCount == 0) {
                        when (type) {
                            AdManager.CSJ -> {
                                if (obj is View) {
                                    LogUtil.d("${AdLogTag.SPLASH_TAG} csj add")
                                    obj?.let {
                                        viewGroup.addView(it as View)
                                    }
                                }
                            }
                            AdManager.GDT -> {
                                if (obj is SplashAD) {
                                    LogUtil.d("${AdLogTag.SPLASH_TAG} gdt add")
                                    (obj as SplashAD).let { it.showAd(viewGroup) }
                                }
                            }
                        }
                    }
                }
            } else {
                thread {
                    SystemClock.sleep(DELAY)
                    ThreadUtil.runOnMainThread {
                        when (type) {
                            AdManager.CSJ -> {
                                if (obj is View) {
                                    obj?.let {
                                        LogUtil.d("${AdLogTag.SPLASH_TAG} csj remove add")
                                        viewGroup.removeAllViews()
                                        viewGroup.addView(it as View)
                                    }
                                }
                            }
                            AdManager.GDT -> {
                                if (obj is SplashAD) {
                                    obj.let {
                                        LogUtil.d("${AdLogTag.SPLASH_TAG} gdt remove add")
                                        viewGroup.removeAllViews()
                                        (obj as SplashAD).showAd(viewGroup)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    //闪屏
    fun openSplash(
        adPositonType: AdPositonType,
        activity: Activity?,
        container: ViewGroup?,
        callback: (Int, Boolean) -> Unit = { splashAdSize, isLoadError -> }
    ) {
        val adPositonConfigBean = AdManager.getAdPositonConfigBeanByType(adPositonType)
        if (adPositonConfigBean == null || adPositonConfigBean.advertisingDataList.size == 0) {
            callback?.invoke(0, false)
            return
        }
        val splashAdSize = adPositonConfigBean.advertisingDataList.size
        var dispose: Disposable? = null
        adPositonConfigBean.advertisingDataList?.apply {
            dispose = RxjavaUtil.count(this.size)?.subscribe {
                val advertisingData = this[it]
                if (AdManager.CSJ == advertisingData.advertisingType) {
                    val adSlot = AdSlot.Builder().setCodeId(advertisingData.adsenseId)
                        .setImageAcceptedSize(1080, 1920).build()
                    val listener =
                        SplashAdListenerAdapter(
                            splashAdSize,
                            advertisingData,
                            activity,
                            container,
                            callback
                        ) {
                            dispose?.apply {
                                if (!isDisposed) {
                                    dispose()
                                }
                            }
                            container?.apply {
                                if (childCount == 0) {
                                    addView(it)
                                }
                            }
                        }
                    activity?.apply {
                        if (this is LifecycleOwner) {
                            this.lifecycle.addObserver(listener)
                        }
                    }
                    TTAdManagerHolder.get().createAdNative(MyApp.app)
                        .loadSplashAd(adSlot, listener, BaseTimeConstants.AD_TIME_OUT)
                } else if (AdManager.GDT == advertisingData.advertisingType) {
                    var splashAD: SplashAD? = null
                    val listener =
                        GdtSplashADListenerAdapter(
                            splashAdSize,
                            advertisingData,
                            container,
                            callback
                        ) {
                            dispose?.apply {
                                if (!isDisposed) {
                                    dispose()
                                }
                            }
                            container?.apply {
                                if (childCount == 0) {
                                    visibility = View.VISIBLE
                                    splashAD?.showAd(this)
                                }
                            }
                        }
                    activity?.apply {
                        if (this is LifecycleOwner) {
                            this.lifecycle.addObserver(listener)
                        }
                    }
                    splashAD = SplashAD(
                        activity,
                        advertisingData.adsenseId,
                        listener,
                        BaseTimeConstants.AD_TIME_OUT
                    )
                    splashAD.fetchAdOnly()
//                    splashAD.fetchAndShowIn(container)
                }
            }
        }
    }
}