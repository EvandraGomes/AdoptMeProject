import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.Components.AnimalCard
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.Components.AnimalCategory
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.Components.AnimalList
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.Controllers.AnimalCategoryController
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.Models.Animal
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ui.theme.Poppins
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val animals = remember { mutableStateOf<List<Animal>>(emptyList()) }
    val isLoading = remember { mutableStateOf(true) }
    val errorMessage = remember { mutableStateOf<String?>(null) }
    val selectedCategory = remember { mutableStateOf("todos") } // Mostrar inicialmente

    LaunchedEffect(selectedCategory.value) {
        val controller = AnimalCategoryController()

        isLoading.value = true // Mostra o indicador de carregamento enquanto busca
        controller.fetchAnimalsByCategory(
            category = selectedCategory.value,
            onResult = { fetchedAnimals ->
                animals.value = fetchedAnimals
                isLoading.value = false
            },
            onError = { error ->
                errorMessage.value = error
                isLoading.value = false
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(120.dp),
                title = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Location",
                            fontSize = 14.sp,
                            fontFamily = Poppins,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(0.dp))
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.location2),
                                contentDescription = "Location",
                                modifier = Modifier.size(20.dp),
                                tint = Color(0XFFF1B5815)
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = "Calomanhengue",
                                fontSize = 18.sp,
                                fontFamily = Poppins,
                                color = Color.Black
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        // Ao clicar no ícone de menu vai para outra tela
                        navController.navigate("MenuScreen")
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu",
                            modifier = Modifier.size(40.dp),
                            tint = Color.Black
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("ProfileScreen")
                    }) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Perfil",
                            modifier = Modifier.size(40.dp),
                            tint = Color.Black
                        )
                    }
                },
                backgroundColor = Color(0xFFF5E8D6),
                elevation = 8.dp
            )
        }
    ) { paddingValues ->
        ContentContainer(modifier = Modifier.padding(paddingValues)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp) // Para manter espaçamento geral
            ) {
                // Categorias de animais
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    AnimalCategory(name = "Gato", imageRes = R.drawable.gatocategory) {
                        selectedCategory.value = "gato" // Atualiza a categoria
                    }
                    AnimalCategory(name = "Cão", imageRes = R.drawable.caocategory) {
                        selectedCategory.value = "cao"
                    }
                    AnimalCategory(name = "Pássaro", imageRes = R.drawable.avecategory) {
                        selectedCategory.value = "passaro"
                    }
                    AnimalCategory(name = "Coelho", imageRes = R.drawable.coelhocategory) {
                        selectedCategory.value = "coelho"
                    }
                }

                Spacer(modifier = Modifier.height(16.dp)) // Espaço entre as categorias e os cards

                // Conteúdo principal
                if (isLoading.value) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 20.dp)
                    )
                } else if (errorMessage.value != null) {
                    Text(
                        "Erro: ${errorMessage.value}",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(12.dp) // Espaçamento entre os cards
                    ) {
                        items(animals.value) { animal ->
                            AnimalCard(animal = animal)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ContentContainer(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 1.dp) // Para não sobrepor a TopAppBar
            .background(
                color = Color(0xFFE7B070),

            )
    ) {
        content()
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
