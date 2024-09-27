package com.example.stock.models.tables

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ProductLocation(
    @SerializedName("product_location_id")
    val product_location_id: Int? = null,

    @SerializedName("product_id")
    val product_id: Int,

    @SerializedName("location_id")
    val location_id: Int,

    @SerializedName("quantity")
    val quantity: Int,

    @SerializedName("updated_at")
    val updated_at: String? = null,

    @SerializedName("created_at")
    val created_at: String? = null
)