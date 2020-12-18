package com.example.myapplication

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import com.example.myapplication.loader.ZipSDCardLoader
import skin.support.SkinCompatManager
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        SkinCompatManager.withoutActivity(this) //初始化 SkinCompatManager 和 创建生命周期监听类
            .setSkinStatusBarColorEnable(true) // 关闭状态栏换肤
            .addStrategy(ZipSDCardLoader())
            //                .setSkinWindowBackgroundEnable(false)           // 关闭windowBackground换肤
                            .setSkinAllActivityEnable(true)                // true: 默认所有的Activity都换肤; false: 只有实现SkinCompatSupportable接口的Activity换肤
            .loadSkin()//如果不设置皮肤名，默认使用sp中存储的皮肤名
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        CalligraphyConfig.initDefault("fonts/Roboto-Regular.ttf", R.attr.fontPath)
    }
}