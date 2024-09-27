package com.example.stock.features.add_product.domain

import android.util.Log
import com.example.stock.models.add_product.ProductWithLoc
import com.example.stock.models.tables.Location
import com.example.stock.models.tables.Product
import com.example.stock.models.tables.ProductLocation
import com.example.stock.models.tables.Warehouse
import com.example.stock.util.Tables.Companion.LOCATIONS
import com.example.stock.util.Tables.Companion.PRODUCTS
import com.example.stock.util.Tables.Companion.PRODUCT_LOCATIONS
import com.example.stock.util.Tables.Companion.WAREHOUSES
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddProductRepository @Inject constructor(
    private val supabaseClient: SupabaseClient
) {

    suspend fun insertData(productWithLoc: ProductWithLoc) = withContext(Dispatchers.IO) {
        try {
            // Insert Warehouse
            val warehouse =
                Warehouse(
                    name = productWithLoc.wareHouseName,
                    address = productWithLoc.wareHouseAddress
                )
            val insertedWarehouse = supabaseClient.postgrest[WAREHOUSES].insert(warehouse) {
                select()
            }.decodeSingle<Warehouse>()

            // Insert Location
            val location =
                Location(
                    warehouse_id = insertedWarehouse.warehouse_id!!,
                    location_code = productWithLoc.locationCode
                )
            val insertedLocation = supabaseClient.postgrest[LOCATIONS].insert(location) {
                select()
            }.decodeSingle<Location>()

            // Insert Product
            val product = Product(
                name = productWithLoc.productName,
                description = productWithLoc.productDesc,
                quantity = productWithLoc.productQuantity
            )
            val insertedProduct = supabaseClient.postgrest[PRODUCTS].insert(product) {
                select()
            }.decodeSingle<Product>()

            // Insert Product_Location
            val productLocation = ProductLocation(
                product_id = insertedProduct.product_id!!,
                location_id = insertedLocation.location_id!!,
                quantity = productWithLoc.productQuantity
            )
            supabaseClient.postgrest[PRODUCT_LOCATIONS].insert(productLocation)
        } catch (e: Exception) {
            Log.d("TAG", "Error inserting data: ${e.message}")
        }
    }
}