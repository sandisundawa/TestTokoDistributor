package com.example.tokodistributorsandi.data.model

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("code_message")
    val codeMessage: String? = null,
    @SerializedName("code_type")
    val codeType: String? = null,
    @SerializedName("data")
    val data: List<Product> = mutableListOf()
)
