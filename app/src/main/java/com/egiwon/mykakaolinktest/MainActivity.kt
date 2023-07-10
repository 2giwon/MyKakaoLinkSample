package com.egiwon.mykakaolinktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.egiwon.mykakaolinktest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnShareKakao.setOnClickListener {
            val util = KakaoUtil(this)
            util.sendKakaoMessage()
        }

        binding.btnScrapKakao.setOnClickListener {
            val util = KakaoUtil(this)
            util.sendKakaoScrap()
        }
    }
}
