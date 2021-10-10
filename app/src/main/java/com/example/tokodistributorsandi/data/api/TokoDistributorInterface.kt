package com.example.tokodistributorsandi.data.api

import com.example.tokodistributorsandi.data.model.BannerResponse
import com.example.tokodistributorsandi.data.model.CategoryResponse
import com.example.tokodistributorsandi.data.model.ProductResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TokoDistributorInterface {

    @GET("utility/home/banner-web")
    fun getBanner(): Single<BannerResponse>

    @GET("utility/home/box-category")
    fun getCategory(@Query("with_staple") with_staple: Boolean): Single<CategoryResponse>

    @GET("product-recommendation")
    fun getProduct(@Query("page") page: Int): Single<ProductResponse>

}