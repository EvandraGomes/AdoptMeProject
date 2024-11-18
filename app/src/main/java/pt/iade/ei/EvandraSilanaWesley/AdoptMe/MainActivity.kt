package pt.iade.ei.EvandraSilanaWesley.AdoptMe

import HomeScreen
import ProfileScreenContent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marcacao.MarcacaoScreen
import com.example.statusscreen.StatusScreenContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ui.theme.AdoptMeTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdoptMeTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "HomeScreen" // ponto de partida
                ) {
                    // Composable para a tela inicial (HomeScreen)
                    composable("HomeScreen") {
                        HomeScreen(navController = navController)
                    }
                    // Composable para a tela do menu (MenuScreenContent)
                    composable("MenuScreen") {
                        MenuScreenContent(navController = navController)
                    }
                    composable("ProfileScreen") {
                        ProfileScreenContent(navController = navController)
                    }
                    composable("MarcacoesScreen"){
                        MarcacaoScreen(navController = navController)
                    }
                    composable("StatusScreen"){
                        StatusScreenContent(navController = navController)
                    }
                }
                    }
                }
            }
        }



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}