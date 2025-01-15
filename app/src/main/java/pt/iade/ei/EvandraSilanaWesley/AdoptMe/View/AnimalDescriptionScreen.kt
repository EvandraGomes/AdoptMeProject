package pt.iade.ei.EvandraSilanaWesley.AdoptMe.View

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import org.json.JSONObject
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.Controllers.AnimalDetailsController
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.Models.Animal
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ui.theme.Poppins


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimalDescriptionScreenContent(
    navController: NavController,
    animalId: Int,
    onVoltarClick: () -> Unit
) {
    val context = LocalContext.current
    val controller = AnimalDetailsController()
    val animal = remember { mutableStateOf<Animal?>(null) }
    val isFavorite = remember { mutableStateOf(false) }

    LaunchedEffect(animalId) {
        animal.value = controller.fetchAnimalDetails(animalId)
    }

    animal.value?.let { animalData ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5E8D6))
        ) {
            // TopBar
            TopAppBar(
                title = {
                    Text(
                        text = "",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onVoltarClick() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color(0xFFF5E8D6)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF5E8D6)
                )
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 56.dp) // espaço para a Top Bar
            ) {
                item {

                    Image(
                        painter = rememberAsyncImagePainter(animalData.ani_image),
                        contentDescription = "Foto do Animal",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(275.dp)
                            .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = animalData.ani_name,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = Color.Black
                        )

                        Image(
                            painter = rememberAsyncImagePainter(
                                model = if (isFavorite.value) R.drawable.favoritar else R.drawable.favoritado
                            ),
                            contentDescription = "Favoritar",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .clickable {
                                    isFavorite.value = !isFavorite.value
                                    handleFavorite(animalData.ani_id, context,
                                        onResult = { result ->
                                            Log.d("Favorito", result)
                                        },
                                        onError = { error ->
                                            Log.e("Favorito", error)
                                        }
                                    )
                                }
                                .shadow(8.dp, CircleShape)
                                .scale(animateFloatAsState(if (isFavorite.value) 1.2f else 1f).value)
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Idade
                        Column(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.White)
                                .padding(16.dp)
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Aniversário \uD83C\uDF82",
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                            Text(
                                text = animalData.ani_birthday,
                                fontFamily = Poppins,
                                fontSize = 12.sp,
                                color = Color.Black.copy(alpha = 0.7f)
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        // Raça
                        Column(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.White)
                                .padding(16.dp)
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Raça",
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                            Text(
                                text = animalData.ani_breed,
                                fontFamily = Poppins,
                                fontSize = 12.sp,
                                color = Color.Black.copy(alpha = 0.7f)
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))

                    // descrição do animal
                    Text(
                        text = animalData.ani_description,
                        fontFamily = Poppins,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                        color = Color.Black.copy(alpha = 0.7f),
                        modifier = Modifier.padding(16.dp)
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(2F.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 40.dp, vertical = 150.dp)
                            .align(Alignment.BottomCenter)
                    ) {
                        Button(
                            onClick = {
                                navController.navigate("MarcacoesScreen/${animalData.ani_id}")
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE7B070)),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .shadow(8.dp, RoundedCornerShape(16.dp))
                        ) {
                            Text(
                                text = "ADOTAR",
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

fun handleFavorite(animalId: Int, context: Context, onResult: (String) -> Unit, onError: (String) -> Unit) {
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val token = sharedPreferences.getString("user_token", "") ?: ""

    if (token.isEmpty()) {
        onError("Token não encontrado.")
        return
    }

    val url = "http://10.0.2.2:8080/api/favorites/add"

    //  corpo da requisição JSON (estrutura com animalId dentro de um objeto)
    val json = JSONObject().apply {
        put("animalId", JSONObject().apply {
            put("id", animalId)
        })
    }

    Log.d("Favorito", "Requisição para: $url com corpo: $json")

    url.httpPost()
        .body(json.toString())
        .header("Content-Type" to "application/json")
        .header("Authorization" to "Bearer $token")
        .responseJson { _, _, result ->
            when (result) {
                is Result.Success -> {
                    val response = result.value.obj()
                    val responseMessage = response.optString("message", "")
                    if (responseMessage.isNotEmpty()) {
                        onResult(responseMessage)
                    } else {
                        onError("Erro ao favoritar o animal.")
                    }
                }

                is Result.Failure -> {
                    val errorMsg = result.error.message ?: "Erro desconhecido"
                    Log.e("Favorito", "Erro na requisição: $errorMsg")
                    onError(errorMsg)
                }
            }
        }
}
