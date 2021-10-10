package com.example.tokodistributorsandi.data.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("icon")
    val icon: String,
    @SerializedName("category_name")
    val categoryName: String
)
