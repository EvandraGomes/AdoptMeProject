package pt.iade.ei.EvandraSilanaWesley.AdoptMe.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ui.theme.Poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoacoesScreenContent(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { }, // Não exibe título
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack, // Ícone da seta
                            contentDescription = "Voltar"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF5E8D6) // Cor de fundo da barra
                )
            )
        }
    ) { innerPadding ->
        // Corpo da tela
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5E8D6))
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 16.dp, bottom = 32.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start // começar pela esquerda
            ) {
                Text(
                    text = "Pontos de doação",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    painter = painterResource(id = R.drawable.paw),
                    contentDescription = "Ícone de pata",
                    tint = Color.Black,
                    modifier = Modifier.size(40.dp)
                )
            }


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f) // ocupa o restante do espaço disponível

            ) {
                Image(
                    painter = painterResource(id = R.drawable.mapailustration),
                    contentDescription = "Mapa",
                    modifier = Modifier
                        .fillMaxWidth().padding(bottom = 50.dp)
                        .fillMaxHeight()
                        .padding(10.dp)
                )
            }

            // Botão de Doação Monetária centralizado
            Button(
                onClick = { navController.navigate("DoacoesMonetariasScreen") },
                modifier = Modifier
                    .fillMaxWidth(0.8f).padding(bottom = 50.dp)
                    .size(70.dp)
                    .padding(bottom = 10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE7B070))
            ) {
                Text(
                    text = "Doação monetária",
                    color = Color.White,
                    fontFamily = Poppins,
                    fontSize = 23.sp,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DoacoesScreenPreview() {
    DoacoesScreenContent(navController = rememberNavController())
}
