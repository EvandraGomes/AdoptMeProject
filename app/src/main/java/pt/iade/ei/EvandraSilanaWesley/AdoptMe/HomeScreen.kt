import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ContentContainerInfo.AnimalList
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ContentContainerInfo.getAnimalList
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ui.theme.Poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
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
                        // Texto "Location"
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
                            // Ícone de localização
                            Icon(
                                painter = painterResource(id = R.drawable.location2),
                                contentDescription = "Location",
                                modifier = Modifier.size(20.dp),
                                tint = Color(0XFFF1B5815)
                            )

                            Spacer(modifier = Modifier.width(3.dp))

                            // Texto "Calomanhengue"
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
        Box(modifier = Modifier.padding(paddingValues)) {
            ContentContainer {
                AnimalList(getAnimalList())
            }
        }
    }
}

// Função para o fundo com bordas arredondadas
@Composable
fun ContentContainer(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 1.dp) // Para não sobrepor a TopAppBar
            .background(
                color = Color(0xFFE7B070),
                shape = RoundedCornerShape(20.dp)
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

