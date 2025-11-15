package com.example.inventory.data.repositories

import com.example.inventory.data.dao.InventoryDao
import com.example.inventory.data.entity.Inventory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InventoryRepository @Inject constructor(
    private val dao: InventoryDao){

     fun getAll():Flow<List<Inventory>> = dao.getAll()

    suspend fun insertAll(products: List<Inventory>) {
        products.forEach { dao.insert(it) }
    }

    suspend fun insert(product: Inventory) = dao.insert(product)

    suspend fun update(product: Inventory) = dao.update(product)

    suspend fun delete(product: Inventory) = dao.delete(product)

    suspend fun deleteById(id:Int) = dao.deleteById(id)

    fun getById(id:Int): Flow<Inventory> = dao.findById(id)

    suspend fun updateById(id: Int, cantidad: Int)= dao.updateCantidad(id,cantidad)

    suspend fun addCantidadById(id: Int, cantidad: Int)=dao.agregarCantidad(id,cantidad)
}