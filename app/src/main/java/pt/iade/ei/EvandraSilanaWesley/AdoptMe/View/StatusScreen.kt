package com.example.statusscreen

import android.content.Context
import android.util.Log
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.json.responseJson
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ui.theme.Poppins



@OptIn(ExperimentalMaterial3Api::class)
@Composable
            fun StatusScreenContent(navController: NavController, taskStates: List<Boolean>) {
                val tasks = listOf(
                    "Primeira visita agendada!",
                    "Avaliação do adotante",
                    "Processo de adoção",
                    "Período de adaptação",
                    "Adoção confirmada",
                    "Acompanhamento pós-Adocao",
                    "Adotado! \uD83C\uDF89"
                )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Cada vez mais perto de um lar \n cheio de carinho!",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = Poppins,
                            color = Color.Black,


                        ),
                        modifier = Modifier.padding(top = 25.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF5E8D6)
                )
            )
        }
    )
    { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFF5E8D6))
                            .padding(innerPadding)
                    ) {

                        Spacer(modifier = Modifier.height(120.dp))

                        tasks.forEachIndexed { index, task ->
                            TaskItem(
                                task = task,
                                isCompleted = taskStates[index],
                                index = index,
                                totalTasks = tasks.size
                            )

                            // Espaçamento entre as tarefas
                            if (index < tasks.size - 1) {
                                Spacer(modifier = Modifier.height(10.dp))
                            }
                        }

                        }

                    }
                }


@Composable
fun TaskItem(
    task: String,
    isCompleted: Boolean,
    index: Int,
    totalTasks: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // estado da bolinha
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        color = if (isCompleted) Color.Blue else Color.LightGray,
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

            // linha vertical fina abaixo da bolinha
            Box(
                modifier = Modifier
                    .width(2.dp)  // espessura
                    .height(20.dp) //  altura
                    .background(
                        color = if (isCompleted) Color.Blue else Color.LightGray
                    )
            )
        }


        Text(
            text = task,
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = Poppins,
                color = if (isCompleted) Color.Black else Color.LightGray,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier.weight(4f)
        )
    }
}

@Preview
@Composable
fun PreviewStatusScreen() {
    val sampleTaskStates = listOf(true, false, false, false, false, false, false)
    StatusScreenContent(navController = rememberNavController(), taskStates = sampleTaskStates)
}
