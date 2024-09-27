package com.example.stock.models.tables

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerializedName("location_id")
    val location_id: Int? = null,

    @SerializedName("warehouse_id")
    val warehouse_id: Int,

    @SerializedName("location_code")
    val location_code: String,

    @SerializedName("created_at")
    val created_at: String? = null
)