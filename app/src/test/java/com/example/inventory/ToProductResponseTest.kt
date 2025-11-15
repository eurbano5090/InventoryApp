package com.example.inventory


import com.example.inventory.data.entity.Inventory
import com.example.inventory.data.entity.toProductResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class ToProductResponseTest {

    @Test
    fun `inventory converts correctly to productResponse`() {

        val inv = Inventory(
            id = 5,
            title = "Polera",
            price = 20.0,
            description = "Desc 2",
            category = "Women",
            image = "img2.png",
            rating = 3.5,
            quantity = 7
        )

        val response = inv.toProductResponse()

        assertEquals(5, response.id)
        assertEquals("Polera", response.title)
        assertEquals(20.0, response.price!!, 0.0)
        assertEquals("Desc 2", response.description)
        assertEquals("Women", response.category)
        assertEquals("img2.png", response.image)
        assertEquals(3.5, response.rating.rate, 0.0)
        assertEquals(7, response.cantidad)
    }
}
