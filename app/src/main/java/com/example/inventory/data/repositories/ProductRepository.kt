package com.example.inventory.data.repositories

import com.example.inventory.data.model.Product
import com.example.inventory.data.model.ProductResponse
import com.example.inventory.data.remote.ApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ProductRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getProducts(): Result<List<ProductResponse>> {
        return try {
            val users = apiService.getProducts()
            Result.success(users)
        } catch (e: IOException) {
            // -> si no hay internet
            Result.failure(Exception("No hay conexión a internet"))
        } catch (e: HttpException) {
            // ->(404, 500, etc.)
            Result.failure(Exception("Error en el servicio: ${e.code()}"))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido: ${e.message}"))
        }
    }
}
