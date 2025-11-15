package com.example.inventory.view.auth.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.entity.Inventory
import com.example.inventory.data.entity.toInventory
import com.example.inventory.data.model.ProductResponse
import com.example.inventory.data.repositories.InventoryRepository
import com.example.inventory.data.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.util.Log
import kotlinx.coroutines.flow.first


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val invRepository: InventoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        // Observa los cambios en Room
        viewModelScope.launch {
            invRepository.getAll().collect { inventory ->
                _uiState.value = _uiState.value.copy(
                    inventory = inventory
                )
            }
        }

        // Carga inicial de productos desde la API
        viewModelScope.launch {
            loadProductsFromApi()
        }
    }

    fun loadProductsFromApi() {
        viewModelScope.launch {
            _uiState.value = HomeUiState(isLoading = true)

            val result = repository.getProducts()
            result.onSuccess { products ->
                val inventario = invRepository.getAll().first()  // <-- obtener la lista actual

                val productosConCantidad = products.map { prod ->
                    val inv = inventario.find { it.id == prod.id }
                    prod.copy(cantidad = inv?.quantity ?: 1)
                }

                // Guardamos en Room
                invRepository.insertAll(productosConCantidad.map { it.toInventory() })

                // Después de insertar, obtenemos el inventario actualizado
                val updatedInventory = invRepository.getAll().first()  // <-- lista actual después de insertar

                _uiState.value = HomeUiState(
                    products = productosConCantidad,
                    inventory = updatedInventory,
                    isLoading = false
                )
            }.onFailure { e ->
                // Si falla la API, mostramos lo local
                loadInventoryOnly()
            }
        }
    }



    fun loadInventoryOnly() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val localInventory = invRepository.getAll().first()

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                inventory = localInventory
            )
        }
    }

    fun insertarCantidad(product: Inventory) = viewModelScope.launch {
        invRepository.insert(product)
    }

    fun getAllInventario() = liveData {
        emit(invRepository.getAll())
    }

    fun actualizarProducto(product: Inventory) = viewModelScope.launch {
        invRepository.update(product)
    }
    fun actualizarCantidad(id: Int, cantidad: Int)=  viewModelScope.launch {
        invRepository.updateById(id,cantidad)
        loadInventoryOnly()
    }

    fun eliminarProducto(product: Inventory) = viewModelScope.launch {
        invRepository.delete(product)
        loadInventoryOnly()
    }

    fun eliminarProductoById(id: Int) = viewModelScope.launch {
        val deletedRows = invRepository.deleteById(id)
        Log.d("ROOM", "Filas borradas: $deletedRows")

        loadInventoryOnly()
    }

    fun addCantidad(id: Int,cantidad: Int) = viewModelScope.launch {
        invRepository.addCantidadById(id,cantidad)
        loadInventoryOnly()
    }

    fun getProductById(id: Int): Flow<Inventory> = invRepository.getById(id)

    fun getById(id: Int) = viewModelScope.launch {
        invRepository.getById(id)
    }
}


data class HomeUiState(
    val products: List<ProductResponse> = emptyList(),
    val inventory: List<Inventory> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null

)
