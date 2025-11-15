package com.example.inventory.data.model

data class ProductResponse(
    val id: Int,
    val title:String?,
    val price: Double?,
    val description: String?,
    val category:String?,
    val image :String?,
    val rating :Rating,
    val cantidad: Int = 1
)

