package com.example.inventory.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.inventory.data.model.ProductResponse
import com.example.inventory.data.model.Rating

@Entity(tableName = "inventory_products")
data class Inventory(
    @PrimaryKey val id: Int,
    val title: String?,
    val price: Double?,
    val description: String?,
    val category: String?,
    val image: String?,
    val rating: Double,
    var quantity: Int = 1
)

fun ProductResponse.toInventory(): Inventory {
    return Inventory(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        image = image,
        rating = rating.rate,
        quantity = cantidad
    )
}

fun Inventory.toProductResponse(): ProductResponse {
    return ProductResponse(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        image = image,
        rating = Rating(rate = rating ?: 0.0, count = 0), // o null si prefieres
        cantidad = quantity
    )
}
