package com.example.marcacao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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
fun MarcacaoScreen(navController: NavHostController) {
    // Data de exemplo para mostrar por cima dos botões
    val currentDate = remember { mutableStateOf("25-11-2024") } // Data fictícia

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
                    containerColor = Color(0xFFF5E8D6) // Cor de fundo da barra
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF5E8D6))
                .verticalScroll(rememberScrollState()) // Caso a tela precise ser rolada
        ) {
            // Imagem grande (metade da tela)
            Image(
                painter = painterResource(id = R.drawable.calendary),
                contentDescription = "Calendário",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
                    .padding(top = 16.dp)
            )

            // Espaço para a data
            Spacer(modifier = Modifier.height(20.dp))

            // Texto com a data por cima dos botões
            Text(
                text = currentDate.value,
                style = TextStyle(
                    fontSize = 25.sp,
                    fontFamily = Poppins,
                    color = Color.Black
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            // Espaço entre a data e os botões
            Spacer(modifier = Modifier.height(40.dp))

            // Botões um embaixo do outro
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { /* Ação do botão */ },
                    modifier = Modifier
                        .size(width = 154.dp, height = 55.dp)
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE7B070))
                ) {
                    Text(text = "10:30", color = Color.Black, fontFamily = Poppins, fontSize = 20.sp)
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { /* Ação do botão */ },
                    modifier = Modifier
                        .size(width = 154.dp, height = 55.dp)
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE7B070))
                ) {
                    Text(text = "12:00", color = Color.Black, fontFamily = Poppins, fontSize = 20.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MarcacaoScreenPreview() {
    MarcacaoScreen(navController = rememberNavController())
}
