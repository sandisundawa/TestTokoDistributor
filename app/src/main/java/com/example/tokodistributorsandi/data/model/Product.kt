package com.example.tokodistributorsandi.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    val id: Int? = null,

    @SerializedName("supplier_code")
    val supplierCode: String? = null,

    @SerializedName("supplier_name")
    val supplierName: String? = null,

    val location: String? = null,

    @SerializedName("total_sold")
    val totalSold: Int? = null,

    @SerializedName("image_name")
    val imageName: String? = null,

    @SerializedName("product_slug")
    val productSlug: String? = null,

    @SerializedName("product_id")
    val productID: Int? = null,

    @SerializedName("product_code")
    val productCode: String? = null,

    @SerializedName("product_name")
    val productName: String? = null,

    @SerializedName("product_score_review")
    val productScoreReview: Int? = null,

    @SerializedName("product_total_review")
    val productTotalReview: Int? = null,

    @SerializedName("product_total_favourite")
    val productTotalFavourite: Int? = null,

    @SerializedName("variant_id")
    val variantID: Int? = null,

    @SerializedName("variant_descriptions")
    val variantDescriptions: String? = null,

    @SerializedName("variant_stock")
    val variantStock: Int? = null,

    @SerializedName("product_stock")
    val productStock: Int? = null,

    val sku: String? = null,

    @SerializedName("main_menu_id")
    val mainMenuID: Int? = null,

    @SerializedName("sub_menu_id")
    val subMenuID: Int? = null,

    @SerializedName("sub_menucategory_id")
    val subMenucategoryID: Int? = null,

    @SerializedName("mainmenu_name")
    val mainmenuName: String? = null,

    @SerializedName("submenu_name")
    val submenuName: String? = null,

    @SerializedName("sub_menucategory_name")
    val subMenucategoryName: String? = null,

    @SerializedName("mainmenu_slug")
    val mainmenuSlug: String? = null,

    @SerializedName("submenu_slug")
    val submenuSlug: String? = null,

    @SerializedName("submenu_category_slug")
    val submenuCategorySlug: String? = null,

    @SerializedName("normal_price")
    val normalPrice: Int? = null,

    @SerializedName("agent_price")
    val agentPrice: Int? = null,

    @SerializedName("member_price")
    val memberPrice: Int? = null,

    @SerializedName("agent_commision")
    val agentCommision: Int? = null,

    @SerializedName("wajib_insurance")
    val wajibInsurance: Int? = null,

    @SerializedName("product_warranty")
    val productWarranty: String? = null,

    @SerializedName("product_condition")
    val productCondition: Int? = null,

    @SerializedName("product_original")
    val productOriginal: Int? = null,

    @SerializedName("discount_percentage")
    val discountPercentage: Int? = null,

    @SerializedName("is_rfc")
    val isRFC: Boolean? = null,

    @SerializedName("total_view")
    val totalView: Int? = null,

    @SerializedName("image_uri")
    val imageURI: String? = null,

    val created: String? = null,

    @SerializedName("updated_at")
    val updatedAt: String? = null,

    @SerializedName("is_has_video")
    val isHasVideo: Boolean? = null,

    @SerializedName("is_cod")
    val isCod: Int? = null,

    @SerializedName("is_autoresi")
    val isAutoresi: Int? = null,

    @SerializedName("is_tokoku")
    val isTokoku: Int? = null,

    @SerializedName("supplier_score_review")
    val supplierScoreReview: Int? = null
)
