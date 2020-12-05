package com.example.robustatask.network

import com.example.robustatask.model.ProductResponse
import retrofit2.http.GET

interface Api {
    @GET("https://api.themoviedb.org/3/movie/550/lists?api_key=2caf1c01467b1e15fd0b4ae9c1b6a152&language=en-US&page=1")
    suspend fun getProductList(): ProductResponse

}
