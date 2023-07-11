package com.egiwon.mykakaolinktest

import android.content.ActivityNotFoundException
import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.share.ShareClient
import com.kakao.sdk.share.WebSharerClient
import com.kakao.sdk.template.model.Content
import com.kakao.sdk.template.model.FeedTemplate
import com.kakao.sdk.template.model.Link
import com.kakao.sdk.user.UserApiClient

class KakaoUtil(
    private val context: Context
) {

    private lateinit var token: OAuthToken

    fun login() {
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
                toast("로그인 실패")
            } else if (token != null) {
                this.token = token.copy()
            }
        }
    }

    fun sendKakaoMessage(
        imageUrl: String = "http://k.kakaocdn.net/dn/Q2iNx/btqgeRgV54P/VLdBs9cvyn8BJXB3o7N8UK/kakaolink40_original.png",
        webUrl: String = "https://developers.kakao.com"
    ) {
        val defaultFeed = FeedTemplate(
            content = Content(
                title = "테스트",
                description = "카달로그 이미지",
                imageUrl = imageUrl,
                link = Link(webUrl = webUrl),
            )
        )

        if (ShareClient.instance.isKakaoTalkSharingAvailable(context)) {
            ShareClient.instance.shareDefault(context, defaultFeed) { sharingResult, error ->
                if (error != null) {
                    toast("공유 실패 $error")
                } else if (sharingResult != null) {
                    context.startActivity(sharingResult.intent)
                }
            }
        } else {
            webShareKakao(WebSharerClient.instance.makeDefaultUrl(defaultFeed))
        }
    }

    private fun webShareKakao(shareUrl: Uri) {
        try {
            KakaoCustomTabsClient.openWithDefault(context, shareUrl)
        } catch (e: UnsupportedOperationException) {
            toast(e.message.toString())
        }

        try {
            KakaoCustomTabsClient.open(context, shareUrl)
        } catch (e: ActivityNotFoundException) {
            toast(e.message.toString())
        }
    }

    fun sendKakaoScrap(
        imageUrl: String = "http://k.kakaocdn.net/dn/Q2iNx/btqgeRgV54P/VLdBs9cvyn8BJXB3o7N8UK/kakaolink40_original.png",
    ) {
        if (ShareClient.instance.isKakaoTalkSharingAvailable(context)) {
            ShareClient.instance.shareScrap(context, imageUrl) { sharingResult, error ->
                if (error != null) {
                    toast("공유 실패 $error")
                } else if (sharingResult != null) {
                    context.startActivity(sharingResult.intent)
                }
            }
        } else {
            webShareKakao(WebSharerClient.instance.makeScrapUrl(imageUrl))
        }
    }

    private fun toast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }
}

