package ian.pf.practica1moviles23100067.presentation.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

data class SportCar(
    val marca: String,
    val modelo: String,
    val precio: Double,
    val imageUrl: String
)

val mockCars = listOf(
    SportCar("Ferrari", "F8 Tributo", 320000.0, "https://cdn.pixabay.com/photo/2020/06/04/18/52/ferrari-5259647_1280.jpg"),
    SportCar("Lamborghini", "Huracán EVO", 280000.0, "https://cdn.pixabay.com/photo/2016/11/29/03/53/lamborghini-1868721_1280.jpg"),
    SportCar("Porsche", "911 Turbo S", 200000.0, "https://cdn.pixabay.com/photo/2017/01/06/19/15/porsche-1957037_1280.jpg"),
    SportCar("McLaren", "720S", 300000.0, "https://cdn.pixabay.com/photo/2020/06/04/18/52/mclaren-5259646_1280.jpg"),
    SportCar("Aston Martin", "Vantage", 180000.0, "https://cdn.pixabay.com/photo/2017/08/30/07/52/aston-martin-2692755_1280.jpg")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(navController: NavController) {
    val total = mockCars.sumOf { it.precio }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo de Autos Deportivos") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(mockCars) { car ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("${car.marca} ${car.modelo}", style = MaterialTheme.typography.titleMedium)
                            Spacer(modifier = Modifier.height(8.dp))
                            Image(
                                painter = rememberAsyncImagePainter(car.imageUrl),
                                contentDescription = "${car.marca} ${car.modelo}",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Precio aproximado: $${"%,.2f".format(car.precio)}", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            Text(
                text = "Costo total: $${"%,.2f".format(total)}",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("home") },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Volver al menú principal")
            }
        }
    }
}