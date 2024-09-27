package com.example.stock.models.add_product

data class ProductWithLoc(
    val wareHouseName: String,
    val wareHouseAddress: String,
    val productQuantity: Int,
    val locationCode : String,
    val productName : String,
    val productDesc : String
)
