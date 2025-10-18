package ian.pf.practica1moviles23100067

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ian.pf.practica1moviles23100067.presentation.navigation.AppNavGraph
import ian.pf.practica1moviles23100067.ui.theme.PRACTICA1MOVILES23100067Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PRACTICA1MOVILES23100067Theme {
                AppNavGraph()
            }
        }
    }
}