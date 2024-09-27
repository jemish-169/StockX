package com.example.stock.features.add_product.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stock.features.add_product.domain.AddProductRepository
import com.example.stock.models.add_product.ProductWithLoc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(private val repository: AddProductRepository) :
    ViewModel() {
    fun insertData(productWithLoc: ProductWithLoc) {
        viewModelScope.launch {
            repository.insertData(productWithLoc)
        }
    }
}