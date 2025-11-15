package com.example.inventory.data.remote


import com.example.inventory.data.model.ProductResponse
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<ProductResponse>
}


