package com.lft.script

/**
 * 项目依赖版本统一管理
 *
 * @author frank
 */
object DependencyConfig {

    const val applicationId = "com.hiscene.hileia"

    /**
     * 依赖版本号
     *
     */
    object Version {
        // Sdk and tools
        const val compileSdkVersion = 31
        const val minSdkVersion = 21
        const val targetSdkVersion = 27//高于该版本会导致 USBCamera 在 Android10 及以上版本不可用

        // App dependencies
        const val appCompatVersion = "1.3.1"
        const val constraintLayoutVersion = "2.1.1"
        const val coreTestingVersion = "2.0.0"
        const val coroutinesVersion = "1.4.2"
        const val espressoVersion = "3.1.1"
        const val fragmentVersion = "1.3.0"
        const val glideVersion = "4.11.0"
        const val gradleVersion = "4.1.1"
        const val hiltVersion = "2.38.1"
        const val junitVersion = "4.12"
        const val kotlinVersion = "1.5.20"
        const val ktxVersion = "1.3.2"
        const val lifecycleVersion = "2.3.0"
        const val materialVersion = "1.4.0"
        const val navigationVersion = "2.3.3"
        const val gsonVersion = "2.8.5"
        const val retrofitVersion = "2.9.0"
        const val okhttpLoggingVersion = "4.7.2"
        const val okioVersion = "2.10.0"
        const val rxjavaVersion = "3.0.10"
        const val rxAndroidVersion = "3.0.0"
        const val rxbindingVersion = "4.0.0"
        const val mmkvVersion = "1.2.9"
        const val permissionxVersion = "1.6.0"
        const val immersionbarVersion = "3.0.0"
        const val multidexVersion = "2.0.1"
        const val cardviewVersion = "1.0.0"
        const val recyclerviewVersion = "1.2.0"
        const val autosizeVersion = "1.2.1"
        const val utilsVersion = "1.30.1"
        const val refreshVersion = "2.0.3"
        const val recyclerAdapterVersion = "3.0.6"
        const val annotationsVersion = "15.0"

        const val pagingVersion = "3.0.1"
        const val roomVersion = "2.3.0"
        const val runnerVersion = "1.0.1"
        const val truthVersion = "0.42"
        const val testExtJunit = "1.1.0"
        const val uiAutomatorVersion = "2.2.0"
        const val viewPagerVersion = "1.0.0"
        const val workVersion = "2.4.0"
    }

    object Libraries {
        const val roomCompiler = "androidx.room:room-compiler:${Version.roomVersion}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Version.glideVersion}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Version.hiltVersion}"
        const val appCompat = "androidx.appcompat:appcompat:${Version.appCompatVersion}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Version.constraintLayoutVersion}"
        const val ktxCore = "androidx.core:core-ktx:${Version.ktxVersion}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlinVersion}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Version.fragmentVersion}"
        const val livedataKtx =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycleVersion}"
        const val viewmodelKtx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycleVersion}"
        const val navigationKtx =
            "androidx.navigation:navigation-fragment-ktx:${Version.navigationVersion}"
        const val navigationUiKtx =
            "androidx.navigation:navigation-ui-ktx:${Version.navigationVersion}"
        const val pagingKtx = "androidx.paging:paging-runtime-ktx:${Version.pagingVersion}"
        const val hilt = "com.google.dagger:hilt-android:${Version.hiltVersion}"
        const val room = "androidx.room:room-runtime:${Version.roomVersion}"
        const val roomKtx = "androidx.room:room-ktx:${Version.roomVersion}"
        const val viewpager2 = "androidx.viewpager2:viewpager2:${Version.viewPagerVersion}"
        const val cardview = "androidx.cardview:cardview:${Version.cardviewVersion}"
        const val recyclerview = "androidx.recyclerview:recyclerview:${Version.recyclerviewVersion}"
        const val multidex = "androidx.multidex:multidex:${Version.multidexVersion}"
        const val work = "androidx.work:work-runtime-ktx:${Version.workVersion}"
        const val glide = "com.github.bumptech.glide:glide:${Version.glideVersion}"
        const val material = "com.google.android.material:material:${Version.materialVersion}"
        const val autosize = "me.jessyan:autosize:${Version.autosizeVersion}"
        const val utils = "com.blankj:utilcodex:${Version.utilsVersion}"
        const val refresh = "com.scwang.smart:refresh-layout-kernel:${Version.refreshVersion}"
        const val refreshHeader =
            "com.scwang.smart:refresh-header-material:${Version.refreshVersion}"
        const val refreshFooter =
            "com.scwang.smart:refresh-footer-classics:${Version.refreshVersion}"
        const val recyclerAdapter =
            "com.github.CymChad:BaseRecyclerViewAdapterHelper:${Version.recyclerAdapterVersion}"
        const val annotations = "org.jetbrains:annotations:${Version.annotationsVersion}"
        const val rxJava = "io.reactivex.rxjava3:rxjava:${Version.rxjavaVersion}"
        const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Version.rxAndroidVersion}"
        const val rxbinding =
            "com.jakewharton.rxbinding4:rxbinding-core:${Version.rxbindingVersion}"
        const val gson = "com.google.code.gson:gson:${Version.gsonVersion}"
        const val okio = "com.squareup.okio:okio:${Version.okioVersion}"
        const val okhttpLog =
            "com.squareup.okhttp3:logging-interceptor:${Version.okhttpLoggingVersion}"
        const val retrofitConverter =
            "com.squareup.retrofit2:converter-gson:${Version.retrofitVersion}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofitVersion}"
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutinesVersion}"
        const val coroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutinesVersion}"
        const val mmkv = "com.tencent:mmkv-static:${Version.mmkvVersion}"
        const val permissionx =
            "com.guolindev.permissionx:permissionx:${Version.permissionxVersion}"
        const val immersionbar = "com.gyf.immersionbar:immersionbar:${Version.immersionbarVersion}"
        const val immersionbar_components =
            "com.gyf.immersionbar:immersionbar-components:${Version.immersionbarVersion}"
        const val immersionbar_ktx =
            "com.gyf.immersionbar:immersionbar-ktx:${Version.immersionbarVersion}"

        const val coreTesting = "androidx.arch.core:core-testing:${Version.coreTestingVersion}"
        const val espressoContrib =
            "androidx.test.espresso:espresso-contrib:${Version.espressoVersion}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Version.espressoVersion}"
        const val espressoIntents =
            "androidx.test.espresso:espresso-intents:${Version.espressoVersion}"
        const val testExtJunit = "androidx.test.ext:junit:${Version.testExtJunit}"
        const val uiautomator =
            "androidx.test.uiautomator:uiautomator:${Version.uiAutomatorVersion}"
        const val workTesting = "androidx.work:work-testing:${Version.workVersion}"
        const val truth = "com.google.truth:truth:${Version.truthVersion}"
        const val hiltTesting = "com.google.dagger:hilt-android-testing:${Version.hiltVersion}"
        const val coroutinesTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutinesVersion}"
        const val junit = "junit:junit:${Version.junitVersion}"
    }

}