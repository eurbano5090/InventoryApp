package com.example.inventory.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.inventory.data.entity.Inventory
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryDao {

    @Query("SELECT * FROM inventory_products")
    fun getAll(): Flow<List<Inventory>>

    @Query("SELECT * FROM inventory_products WHERE id = :id")
    fun findById(id: Int): Flow<Inventory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<Inventory>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Inventory)

    @Update
    suspend fun update(product: Inventory)

    @Delete
    suspend fun delete(product: Inventory)

    @Query("DELETE FROM inventory_products WHERE id = :id")
    suspend fun deleteById(id: Int): Int

    @Query("UPDATE inventory_products SET quantity = :cantidad WHERE id = :id")
    suspend fun updateCantidad(id: Int, cantidad: Int)

    @Query("UPDATE inventory_products SET quantity = quantity + :cantidad WHERE id = :id")
    suspend fun agregarCantidad(id: Int, cantidad: Int)

}