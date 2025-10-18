package ian.pf.practica1moviles23100067.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Menú Principal") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bienvenido",
                style = MaterialTheme.typography.headlineMedium
            )

            Button(
                onClick = { navController.navigate("calculator") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calculadora de consumo de agua")
            }

            Button(
                onClick = { navController.navigate("registerActivity") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registro de actividad física")
            }

            Button(
                onClick = { navController.navigate("catalog") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Catálogo de Autos deportivos")
            }
        }
    }
}