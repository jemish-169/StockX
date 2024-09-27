package com.example.stock.features.add_product.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.stock.models.add_product.ProductWithLoc

@Composable
fun AddProductScreen(
    viewModel: AddProductViewModel
) {
    ProductWithLocInputForm {
        viewModel.insertData(it)
    }
}

@Composable
fun ProductWithLocInputForm(onSubmit: (ProductWithLoc) -> Unit) {
    var wareHouseName by remember { mutableStateOf("") }
    var wareHouseAddress by remember { mutableStateOf("") }
    var productQuantity by remember { mutableIntStateOf(0) }
    var locationCode by remember { mutableStateOf("") }
    var productName by remember { mutableStateOf("") }
    var productDesc by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = wareHouseName,
            onValueChange = { wareHouseName = it },
            label = { Text("Warehouse Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = wareHouseAddress,
            onValueChange = { wareHouseAddress = it },
            label = { Text("Warehouse Address") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = locationCode,
            onValueChange = { locationCode = it },
            label = { Text("Location Code") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = productName,
            onValueChange = { productName = it },
            label = { Text("Product Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = productDesc,
            onValueChange = { productDesc = it },
            label = { Text("Product Description") },
            modifier = Modifier.fillMaxWidth()
        )

        // Quantity input with minus and plus buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(onClick = { if (productQuantity > 0) productQuantity-- }) {
                Text("-")
            }

            OutlinedTextField(
                value = productQuantity.toString(),
                onValueChange = {
                    val newValue = it.toIntOrNull() ?: 0
                    productQuantity = newValue.coerceAtLeast(0)
                },
                label = { Text("Quantity") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )

            OutlinedButton(onClick = { productQuantity++ }) {
                Text("+")
            }
        }

        Button(
            onClick = {
                val product = ProductWithLoc(
                    wareHouseName = wareHouseName,
                    wareHouseAddress = wareHouseAddress,
                    productQuantity = productQuantity,
                    locationCode = locationCode,
                    productName = productName,
                    productDesc = productDesc
                )
                onSubmit(product)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Submit")
        }
    }
}
