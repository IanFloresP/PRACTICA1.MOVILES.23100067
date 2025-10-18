package ian.pf.practica1moviles23100067.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ian.pf.practica1moviles23100067.presentation.calculatorWater.CalculatorWaterScreen
import ian.pf.practica1moviles23100067.presentation.catalog.CatalogScreen
import ian.pf.practica1moviles23100067.presentation.home.HomeScreen
import ian.pf.practica1moviles23100067.presentation.registActivity.ActivityScreen

@Composable
fun AppNavGraph(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home"){
        composable ("home"){ HomeScreen(navController) }
        composable ("calculatorWater"){ CalculatorWaterScreen(navController) }
        composable ("registerActivity"){ ActivityScreen(navController) }
        composable ("catalog"){  CatalogScreen(navController) }
    }
}