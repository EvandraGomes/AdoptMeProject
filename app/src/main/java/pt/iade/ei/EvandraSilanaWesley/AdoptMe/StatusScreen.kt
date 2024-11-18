package com.example.statusscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ui.theme.Poppins


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatusScreenContent(navController: NavHostController) {
    // Lista de tarefas com base em dados da API
    val tasks = listOf(
        "Primeira visita do animal",
        "Avaliacao do adotante",
        "Processo de adocao",
        "Periodo de adaptacao",
        "Adocao confirmada",
        "Acompanhamento pos-Adocao",
        "Adotado!"
    )

    // o API tem de re3ceber o estado de cada task
    var taskStates by remember {
        mutableStateOf(List(tasks.size) { false }) // Inicialmente todas as tarefas são não concluídas
    }

    // Simulador do comportamento da API (1 e 4, como true)
    LaunchedEffect(Unit) {

        taskStates = listOf(true, true, false, false, false, false, false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("HomeScreen") }) {
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
        }
    ) { innerPadding ->
        // Corpo do conteúdo da tela, com o espaçamento do Scaffold
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(1.dp)
                .background(Color(0xFFF5E8D6))
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(110.dp))

            tasks.forEachIndexed { index, task ->
                TaskItem(
                    task = task,
                    isCompleted = taskStates[index]
                )
                if (index < tasks.size - 1) {
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

@Composable
fun TaskItem(task: String, isCompleted: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Bolinha verde ou cinza dependendo do estado
        Box(
            modifier = Modifier
                .size(24.dp)
                .padding(4.dp)
                .background(
                    color = if (isCompleted) Color.Green else Color.Gray,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {

            if (isCompleted) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Concluída",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(60.dp))

        // Texto da tarefa
        Text(
            text = task,
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = Poppins,

            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StatusScreenPreview() {
    StatusScreenContent(navController = rememberNavController())
}
