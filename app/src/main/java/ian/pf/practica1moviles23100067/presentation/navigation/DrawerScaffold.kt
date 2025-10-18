package ian.pf.practica1moviles23100067.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerScaffold(navController: NavController, content: @Composable () -> Unit) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Menú Principal", modifier = Modifier.padding(16.dp))

                NavigationDrawerItem(
                    label = { Text("Inicio") },
                    selected = false,
                    onClick = {
                        navController.navigate("home")
                        scope.launch { drawerState.close() }
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Calculadora de agua") },
                    selected = false,
                    onClick = {
                        navController.navigate("calculator")
                        scope.launch { drawerState.close() }
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Registro físico") },
                    selected = false,
                    onClick = {
                        navController.navigate("registerActivity")
                        scope.launch { drawerState.close() }
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Catálogo de autos") },
                    selected = false,
                    onClick = {
                        navController.navigate("catalog")
                        scope.launch { drawerState.close() }
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("App UESAN") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menú")
                        }
                    }
                )
            }
        ) { padding ->
            Surface(modifier = Modifier.padding(padding)) {
                content()
            }
        }
    }
}