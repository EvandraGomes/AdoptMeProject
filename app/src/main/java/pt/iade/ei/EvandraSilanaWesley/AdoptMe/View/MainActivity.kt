package pt.iade.ei.EvandraSilanaWesley.AdoptMe.View



import ProfileScreenContent
import WelcomeScreen
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marcacao.MarcacoesScreen
// import com.example.marcacao.MarcacaoScreen
import com.example.statusscreen.StatusScreenContent
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.Components.HomeScreen
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ui.theme.AdoptMeTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdoptMeTheme {
                val navController = rememberNavController()
                val context = LocalContext.current
                val addFavorite: (Int, String) -> Unit = { animalId, token ->
                    println("Animal com ID $animalId adicionado aos favoritos com o token $token")
                }

                NavHost(
                    navController = navController,
                    startDestination = "WelcomeScreen" // Tela inicial
                ) {
                    composable("WelcomeScreen") {
                        WelcomeScreen(navController = navController)
                    }

                    composable("LoginScreen") {
                        LoginScreenContent(navController = navController, context = context)
                    }

                    composable("BlogPostScreen") {
                        BlogPostScreen(navController = navController)
                    }

                    composable("ProfileScreen") {
                        ProfileScreenContent(navController = navController, context = context)
                    }

                    composable("HomeScreen") {
                        HomeScreen(navController = navController)
                    }

                    composable("SigninScreen") {
                        SigninScreenContent(navController = navController)
                    }

                    composable("MenuScreen") {
                        MenuScreenContent(navController = navController)
                    }
                    composable("MarcacoesScreen/{animalId}") { backStackEntry ->
                        val animalId = backStackEntry.arguments?.getString("animalId")?.toIntOrNull() ?: 0
                        MarcacoesScreen(navController = navController, animalId = animalId)
                    }
                    composable("StatusScreen") {

                        StatusScreenContent(navController = navController, taskStates = listOf(false, false, false, false, false, false, false))
                    }


                    composable("DoacoesScreen") {
                        DoacoesScreenContent(navController = navController)
                    }
                    composable("FavScreen") {
                        FavScreenContent(context = LocalContext.current, navController = navController)
                    }

                    composable("DoacoesMonetariasScreen") {
                        DoacoesMonetariasScreenContent(navController = navController)
                    }

                    composable("AnimalDescriptionScreen/{animalId}") { backStackEntry ->
                        val animalId = backStackEntry.arguments?.getString("animalId")?.toInt() ?: 0
                        AnimalDescriptionScreenContent(
                            navController = navController,
                            animalId = animalId,
                            onVoltarClick = { navController.popBackStack() },

                        )
                    }


                }
                    }
                }
            }
        }





    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun HomeScreenPreview() {
        val navController = rememberNavController()
        HomeScreen(navController = navController)
    }

