package com.example.robustatask.model

import com.example.robustatask.network.Api
import com.example.robustatask.network.HandleResponseBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract  class ProductRepository(private val api: Api) {

    private  suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): HandleResponseBody<T> {
        return withContext(Dispatchers.IO) {
            try {
                HandleResponseBody.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        HandleResponseBody.Failure(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        HandleResponseBody.Failure(true, null, null)
                    }
                }
            }
        }
    }


    suspend fun getListOfProducts() = safeApiCall {
        api.getProductList()
    }
}