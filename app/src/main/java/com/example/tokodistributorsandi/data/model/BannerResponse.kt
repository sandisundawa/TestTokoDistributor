package com.example.tokodistributorsandi.data.model

import com.google.gson.annotations.SerializedName

data class BannerResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("code_message")
    val codeMessage: String,
    @SerializedName("code_type")
    val codeType: String,
    @SerializedName("data")
    val data: List<Banner>
)
