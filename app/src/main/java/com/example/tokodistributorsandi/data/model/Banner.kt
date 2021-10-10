package com.example.tokodistributorsandi.data.model

import com.google.gson.annotations.SerializedName

data class Banner(
    @SerializedName("deep_link")
    val deepLink: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("url_mobile")
    val urlMobile: String,
)
