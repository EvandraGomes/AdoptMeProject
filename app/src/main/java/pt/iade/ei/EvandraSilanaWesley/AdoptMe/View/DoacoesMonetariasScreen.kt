package pt.iade.ei.EvandraSilanaWesley.AdoptMe.View

import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ui.theme.Poppins
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoacoesMonetariasScreenContent(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF5E8D6)
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF5E8D6))
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Text(
                    text = "Contribua Financeiramente",
                    fontSize = 25.sp,

                    color = Color.DarkGray,
                    modifier = Modifier.padding(top = 30.dp),
                    fontFamily = Poppins
                )


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 70.dp)
                        .size(100.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    val valores = listOf("10€", "20€", "50€")
                    valores.forEach { valor ->
                        Button(
                            onClick = { /* Lógica para selecionar valor */ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            modifier = Modifier
                                .height(90.dp) // diminuir a altura
                                .padding(4.dp)
                                .shadow(8.dp, RoundedCornerShape(12.dp)), // add sombra
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = valor,
                                color = Color.Black,
                                fontFamily = Poppins,
                                fontSize = 30.sp,
                            )
                        }
                    }
                }


                val outroValor = remember { mutableStateOf("") }
                TextField(
                    value = outroValor.value,
                    onValueChange = { outroValor.value = it },
                    label = { Text("Digite outro valor" , fontFamily = Poppins) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(top = 30.dp)
                        .shadow(4.dp, RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White
                )
                )

                Text(
                    text = "Escolha o método de pagamento:",
                    fontSize = 20.sp,

                    color = Color.DarkGray,
                    fontFamily = Poppins,
                    modifier = Modifier.padding(top = 40.dp),
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth().padding(top = 10.dp)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    val metodos = listOf(
                        R.drawable.visa_logo,
                        R.drawable.mbway_logo,
                        R.drawable.paypal_logo
                    )
                    metodos.forEach { icon ->
                        Card(
                            modifier = Modifier
                                .size(100.dp)
                                .padding(8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            shape = RoundedCornerShape(15.dp),
                            elevation = CardDefaults.cardElevation(5.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = icon),
                                    contentDescription = null,
                                    modifier = Modifier.size(70.dp),
                                    contentScale = ContentScale.Fit
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {  },
                    modifier = Modifier
                        .fillMaxWidth(0.8f).padding(bottom = 80.dp)
                        .size(80.dp)
                        .padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE7B070))
                ) {
                    Text(
                        text = "Confirmar Doação",
                        color = Color.White,
                        fontFamily = Poppins,
                        fontSize = 23.sp
                    )
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun DoacoesMonetariasScreenPreview() {
    DoacoesMonetariasScreenContent(navController = rememberNavController())
}
