package com.example.stock.models.tables

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Warehouse(
    @SerializedName("warehouse_id")
    val warehouse_id: Int? = null,

    @SerializedName("name")
    val name: String,

    @SerializedName("address")
    val address: String,

    @SerializedName("updated_at")
    val updated_at: String? = null,

    @SerializedName("created_at")
    val created_at: String? = null
)