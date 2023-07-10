package com.egiwon.mykakaolinktest

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class KakaoSampleApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "fd38c4f22192eac9eeece072492dcbad")
    }
}
