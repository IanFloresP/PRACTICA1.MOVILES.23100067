package ian.pf.practica1moviles23100067.presentation.calculatorWater

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import android.widget.Toast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorWaterScreen(navController: NavController) {
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Sin especificar") }
    var result by remember { mutableStateOf<String?>(null) }

    val genderOptions = listOf("Masculino", "Femenino", "Sin especificar")
    val genderFactor = when (gender) {
        "Masculino" -> 1.02
        "Femenino" -> 1.01
        else -> 1.00
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calculadora de Agua") }
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
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                singleLine = true
            )

            OutlinedTextField(
                value = weight,
                onValueChange = { weight = it },
                label = { Text("Peso corporal (kg)") },
                singleLine = true
            )

            Text("Género")
            genderOptions.forEach { option ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = gender == option,
                        onClick = { gender = option }
                    )
                    Text(option)
                }
            }

            Button(onClick = {
                val weightValue = weight.toFloatOrNull()
                if (name.isBlank() || weight.isBlank()) {
                    Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                    result = null
                } else if (weightValue == null || weightValue !in 5f..200f) {
                    Toast.makeText(context, "El peso debe estar entre 5 y 200 kg", Toast.LENGTH_SHORT).show()
                    result = null
                } else {
                    val liters = weightValue * 0.035 * genderFactor
                    result = "$name debe beber aproximadamente %.2f litros de agua al día".format(liters)
                }
            }) {
                Text("Calcular")
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