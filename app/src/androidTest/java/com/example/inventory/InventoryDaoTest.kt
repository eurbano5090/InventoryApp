package com.example.inventory
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertEquals
import com.example.inventory.data.entity.AppDatabase
import com.example.inventory.data.dao.InventoryDao
import com.example.inventory.data.entity.Inventory
import com.example.inventory.data.model.Rating


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class InventoryDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var dao: InventoryDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        dao = db.inventoryDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun insertAndReadInventory() = runTest {
        val item = Inventory(
            id = 1,
            quantity = 5,
            title = "Test title",
            price = 10.0,
            description = "Test description",
            category = "Test category",
            image = "https://example.com/test.png",
            rating = 4.5
            )


        dao.insert(item)

        val result = dao.getAll().first()

        assertEquals(1, result.size)
        assertEquals(5, result[0].quantity)
    }

    @Test
    fun deleteInventoryItem() = runTest {
        val item = Inventory(
            id = 1,
            quantity = 5,
            title = "Test title",
            price = 10.0,
            description = "Test description",
            category = "Test category",
            image = "https://example.com/test.png",
            rating = 4.5
        )

        // Insertamos un item
        dao.insert(item)

        // Lo eliminamos
        dao.delete(item)

        // Leemos los datos nuevamente
        val result = dao.getAll().first()

        // Debe estar vacío
        assertEquals(0, result.size)
    }


}

