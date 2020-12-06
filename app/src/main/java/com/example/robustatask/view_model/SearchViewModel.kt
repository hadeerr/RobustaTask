package com.example.robustatask.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.robustatask.model.ProductRepository
import com.example.robustatask.model.ProductResponse
import com.example.robustatask.network.HandleResponseBody
import kotlinx.coroutines.launch


class SearchViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    private val _listOfProducts: MutableLiveData<HandleResponseBody<ProductResponse>> = MutableLiveData()
    val productList: LiveData<HandleResponseBody<ProductResponse>>
        get() = _listOfProducts

    fun getList() = viewModelScope.launch {
        _listOfProducts.value = HandleResponseBody.Loading
        _listOfProducts.value = repository.getListOfProducts()
    }
}