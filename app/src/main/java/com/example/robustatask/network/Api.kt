package com.example.robustatask.network

import com.example.robustatask.model.ProductResponse
import retrofit2.http.GET

interface Api {
    @GET("https://movie-database-imdb-alternative.p.rapidapi.com/?s=Avengers%20Endgame&page=1&r=json")
    suspend fun getProductList(): ProductResponse

}