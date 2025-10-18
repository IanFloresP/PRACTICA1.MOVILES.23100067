package ian.pf.practica1moviles23100067.presentation.registActivity

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityScreen(navController: NavController) {
    val context = LocalContext.current

    var selectedActivity by remember { mutableStateOf("") }
    var durationText by remember { mutableStateOf("") }
    var intensity by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }

    val activities = listOf("Correr", "Caminar", "Nadar", "Ciclismo", "Yoga")
    val activityCalories = mapOf(
        "Correr" to 10,
        "Caminar" to 5,
        "Nadar" to 8,
        "Ciclismo" to 7,
        "Yoga" to 4
    )
    val intensityFactors = mapOf(
        "Baja" to 0.8,
        "Media" to 1.0,
        "Alta" to 1.2
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registro de Actividad Física") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Dropdown de actividad
            Text("Tipo de actividad")
            activities.forEach { activity ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedActivity == activity,
                        onClick = { selectedActivity = activity }
                    )
                    Text(activity)
                }
            }

            // Duración
            OutlinedTextField(
                value = durationText,
                onValueChange = { durationText = it },
                label = { Text("Duración (minutos)") },
                singleLine = true
            )

            // Intensidad
            Text("Intensidad")
            listOf("Baja", "Media", "Alta").forEach { level ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = intensity == level,
                        onClick = { intensity = level }
                    )
                    Text(level)
                }
            }

            // Botón de cálculo
            Button(onClick = {
                val duration = durationText.toIntOrNull()
                if (selectedActivity.isBlank() || intensity.isBlank() || durationText.isBlank()) {
                    Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                    result = null
                } else if (duration == null || duration <= 0) {
                    Toast.makeText(context, "La duración debe ser un número entero positivo", Toast.LENGTH_SHORT).show()
                    result = null
                } else {
                    val caloriesPerMin = activityCalories[selectedActivity] ?: 0
                    val factor = intensityFactors[intensity] ?: 1.0
                    val totalCalories = caloriesPerMin * duration * factor
                    result = "Has quemado aproximadamente %.2f calorías.".format(totalCalories)
                }
            }) {
                Text("Calcular calorías")
            }

            result?.let {
                Text(it, style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = { navController.navigate("home") }) {
                Text("Volver al menú principal")
            }
        }
    }
}