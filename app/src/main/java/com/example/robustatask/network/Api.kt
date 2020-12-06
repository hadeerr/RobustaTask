package com.example.robustatask.network

import com.example.robustatask.model.ProductResponse
import retrofit2.http.GET

interface Api {
    @GET("user")
    suspend fun getProductList(): ProductResponse

}