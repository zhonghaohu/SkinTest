package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.View
import skin.support.SkinCompatManager
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class MainActivity : FragmentActivity() {

    override fun attachBaseContext(newBase: Context?) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))//2.3.0使用
        super.attachBaseContext(CalligraphyContextWrapper(newBase))//低版本使用
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val testFragment = TestFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fl_fragment,testFragment).commit()
    }
    var switchStatus = false
    fun changeSkin(view: View) {
        if(!switchStatus){
//            SkinCompatManager.getInstance().loadSkin("night.skin",SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS)
            SkinCompatManager.getInstance().loadSkin("night",SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN)
            switchStatus = true
        }else{
            switchStatus = false
            SkinCompatManager.getInstance().restoreDefaultTheme()
        }
    }

    override fun onStart() {
        super.onStart()
    }

}