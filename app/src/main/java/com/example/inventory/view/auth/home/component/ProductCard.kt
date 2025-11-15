package com.example.inventory.view.auth.home.component
import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.inventory.R
import com.example.inventory.data.entity.Inventory
import com.example.inventory.view.auth.home.HomeViewModel


@SuppressLint("SuspiciousIndentation", "DefaultLocale")
@Composable
fun ProductCard(prod: Inventory,
                navController: NavController,
                homeViewModel: HomeViewModel = hiltViewModel()
){

    val imageUrl = prod.image ?.replace("http://", "https://")
    val precioPesos = (prod.price ?: 0.0) * 936.13

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding( 4.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(modifier = Modifier.width(8.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    AsyncImage(
                        model = imageUrl,
                        contentDescription ="Libro sin título",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .width(90.dp)
                            .padding(15.dp),
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(R.drawable.ic_book_placeholder),
                        error = painterResource(R.drawable.ic_book_placeholder)
                    )
                    Text(
                        text = "Nombre : ${prod.title ?: "Sin nombre"}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight =  FontWeight.Bold,
                        color =MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "sku : ${prod.id}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight =  FontWeight.Bold,
                        color =MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Precio: \$${String.format("%.0f", precioPesos)} ",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight =  FontWeight.Bold,
                        color =MaterialTheme.colorScheme.tertiary
                    )

                    Text(
                        text = "Categoria : ${prod.category ?: "Sin categoria"}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight =  FontWeight.Bold,
                        color =MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Cantidad: ${prod.quantity}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = "Rating ⭐ : ${prod.rating ?: "Sin rating"}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight =  FontWeight.Bold,
                        color =MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        // -> Agregar cantidad
                        FloatingActionButton(
                            onClick = { navController.navigate("add/${prod.id}") },
                            containerColor = MaterialTheme.colorScheme.primary
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_add),
                                contentDescription = "Agregar cantidad"
                            )
                        }

                        // -> Editar producto
                        FloatingActionButton(
                            onClick = { navController.navigate("edit/${prod.id}") },
                            containerColor = MaterialTheme.colorScheme.tertiary
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_edit),
                                contentDescription = "Editar producto"
                            )
                        }

                        // -> Eliminar
                        FloatingActionButton(
                            onClick = { homeViewModel.eliminarProductoById(prod.id)},
                            containerColor = MaterialTheme.colorScheme.error
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_delete),
                                contentDescription = "Eliminar producto"
                            )
                        }
                    }
                }

            }
        }
    }