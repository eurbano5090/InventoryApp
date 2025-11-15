package com.example.inventory

import com.example.inventory.data.entity.toInventory
import com.example.inventory.data.model.ProductResponse
import com.example.inventory.data.model.Rating
import org.junit.Assert.assertEquals
import org.junit.Test

class InventoryMappingTest {

    @Test
    fun productResponseToInventory_convertsCorrectly() {

        val response = ProductResponse(
            id = 1,
            title = "Polera",
            price = 20.0,
            description = "Roja",
            category = "ropa",
            image = "img.png",
            rating = Rating(rate = 4.5, count = 10),
            cantidad = 3
        )

        val result = response.toInventory()

        assertEquals(response.id, result.id)
        assertEquals(response.title, result.title)
        assertEquals(response.price ?: 0.0, result.price ?: 0.0, 0.0)
        assertEquals(response.description, result.description)
        assertEquals(response.category, result.category)
        assertEquals(response.image, result.image)
        assertEquals(response.rating.rate, result.rating ?: 0.0, 0.0)
        assertEquals(response.cantidad, result.quantity)
    }



}

