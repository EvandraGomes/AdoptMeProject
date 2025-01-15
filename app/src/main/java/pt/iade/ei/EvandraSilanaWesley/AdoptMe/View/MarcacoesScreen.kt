package com.example.marcacao

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.json.responseJson
import org.json.JSONObject
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ui.theme.Poppins
import java.text.SimpleDateFormat
import java.util.*

fun atualizarStatus(
    animalId: Int,
    token: String,
    taskStates: MutableState<List<Boolean>>,
    context: Context
) {
    val url = "http://10.0.2.2:8080/api/status/$animalId"
    val json = JSONObject().apply {
        put("animalId", animalId)
    }

    try {
        Fuel.put(url)
            .body(json.toString())
            .header("Content-Type" to "application/json")
            .header("Authorization" to "Bearer $token")
            .responseJson { _, _, result ->
                result.fold(
                    success = { response ->
                        Log.d("Agendamento", "Sucesso: ${response.obj()}")
                    },
                    failure = { error ->
                        Log.e("Agendamento", "Erro: ${error.message}")
                    }
                )
            }
    } catch (e: Exception) {
        Log.e("Erro", "Erro inesperado: ${e.message}")
        Toast.makeText(context, "Erro inesperado!", Toast.LENGTH_SHORT).show()
    }
}
@Composable
fun MarcacoesScreen(animalId: Int, navController: NavController) {
    val context = LocalContext.current
    var selectedDate by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }
    var taskStates = remember { mutableStateOf(List(7) { false }) }
    var showPopup by remember { mutableStateOf(false) }

    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val token = sharedPreferences.getString("user_token", "") ?: ""

    fun confirmarAgendamento() {
        if (selectedDate.isNotEmpty() && selectedTime.isNotEmpty() && token.isNotEmpty()) {
            val url = "http://10.0.2.2:8080/api/visits/schedule"
            val json = JSONObject().apply {
                put("animalId", animalId)
                put("visitDate", selectedDate)
                put("visitTime", selectedTime)
            }

            Fuel.post(url)
                .body(json.toString())
                .header("Content-Type" to "application/json")
                .header("Authorization" to "Bearer $token")
                .responseJson { _, _, result ->
                    result.fold(
                        success = { response ->
                            Log.d("Agendamento", "Sucesso: ${response.obj()}")
                            Toast.makeText(context, "Agendamento realizado com sucesso!", Toast.LENGTH_SHORT).show()
                            atualizarStatus(animalId, token, taskStates, context)

                            // Mostrar popup e depois navegar
                            showPopup = true
                        },
                        failure = { error ->
                            Log.e("Agendamento", "Erro: ${error.message}")
                            Toast.makeText(context, "Erro ao agendar!", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
        } else {
            Toast.makeText(context, "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show()
        }
    }


    LaunchedEffect(showPopup) {
        if (showPopup) {
            kotlinx.coroutines.delay(2000) // Atraso de 2 segundos
            navController.navigate("StatusScreen/${taskStates.value.joinToString(",")}")
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.wppmarcacoes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 90.dp, end = 70.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(text = "Agendar Visita", fontSize = 20.sp, style = TextStyle(color = Color.Black), fontFamily = Poppins)

            Spacer(modifier = Modifier.height(10.dp))

            Text(text = "Animal ID: $animalId", fontSize = 16.sp, style = TextStyle(color = Color.Black), fontFamily = Poppins)

            Spacer(modifier = Modifier.height(50.dp))

            // Botão de selecionar data
            DatePickerButton(onDateSelected = { selectedDate = it })

            Spacer(modifier = Modifier.height(20.dp))

            // Botão de selecionar hora
            TimePicker(onTimeSelected = { selectedTime = it })

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { confirmarAgendamento() },
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(45.dp)
                    .padding(top = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EA))
            ) {
                Text(text = "Confirmar", fontSize = 14.sp, color = Color.White, fontFamily = Poppins)
            }
        }
        if (showPopup) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.8f), shape = RoundedCornerShape(10.dp))
                    .padding(top = 4.dp, start = 80.dp), contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier.padding(16.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Visita Agendada 🍀",
                            style = TextStyle(fontSize = 18.sp, color = Color.Black, fontFamily = Poppins)
                        )
                    }
                }
            }
        }
    }
}




@Composable
fun DatePickerButton(onDateSelected: (String) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf("") }

    Button(
        onClick = { showDialog = true },
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(45.dp)
            .padding(top = 4.dp, start = 80.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03DAC6))
    ) {
        Text(
            text = if (selectedDate.isEmpty()) "Escolher Data" else "Data: $selectedDate",
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = Poppins
        )
    }

    if (showDialog) {
        DatePickerDialog(onDateSelected = { date ->
            selectedDate = date
            onDateSelected(date)
            showDialog = false
        })
    }
}

@Composable
fun DatePickerDialog(onDateSelected: (String) -> Unit) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    val datePickerDialog = android.app.DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = "${selectedYear}-${selectedMonth + 1}-${selectedDay}"
            onDateSelected(dateFormat.format(dateFormat.parse(selectedDate)))
        },
        year, month, day
    )

    datePickerDialog.show()
}

@Composable
fun TimePicker(onTimeSelected: (String) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedTime by remember { mutableStateOf("00:00") }

    Button(
        onClick = { showDialog = true },
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(45.dp)
            .padding(top = 4.dp , start = 80.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF018786))
    ) {
        Text(
            text = if (selectedTime == "00:00") "Escolher Hora" else "Hora: $selectedTime",
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = Poppins
        )
    }

    if (showDialog) {
        val context = LocalContext.current
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        android.app.TimePickerDialog(
            context,
            { _, selectedHour, selectedMinute ->
                selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                onTimeSelected(selectedTime)
            },
            hour, minute, true
        ).show()
    }
}

@Composable
@Preview(showBackground = true, name = "Tela de Agendamento")
fun PreviewMarcacoesScreen() {
    MarcacoesScreen(animalId = 1, navController = NavController(LocalContext.current))
}
