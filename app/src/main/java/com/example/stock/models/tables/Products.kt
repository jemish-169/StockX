package com.example.stock.models.tables

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerializedName("product_id")
    val product_id: Int? = null,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("quantity")
    val quantity: Int,

    @SerializedName("created_at")
    val created_at: String? = null,

    @SerializedName("updated_at")
    val updated_at: String? = null
)