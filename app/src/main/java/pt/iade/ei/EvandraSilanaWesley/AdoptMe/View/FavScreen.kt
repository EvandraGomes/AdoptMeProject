package pt.iade.ei.EvandraSilanaWesley.AdoptMe.View

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.Models.Animal
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ui.theme.Poppins
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FavScreenContent(context: Context, navController: NavController) {
    var favorites by remember { mutableStateOf<List<Animal>>(emptyList()) }
    var errorMessage by remember { mutableStateOf("") }

    // Carrega os favoritos ao iniciar a tela
    LaunchedEffect(Unit) {
        fetchFavorites(
            context = context,
            onSuccess = { animals -> favorites = animals },
            onError = { error -> errorMessage = error }
        )
    }

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFF5E8D6)) // Fundo da tela
    ) {
        // TopBar com cor F5E8D6
        TopAppBar(
            title = {
                Text(
                    text = "Favoritos",
                    color = MaterialTheme.colors.onPrimary,
                    fontSize = 20.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold
                )
            },
            backgroundColor = Color(0xFFF5E8D6), // Cor da topbar
            modifier = Modifier
                .height(0.dp)
        )

        if (errorMessage.isNotEmpty()) {
            // Exibe a mensagem de erro
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        } else {
            // Exibe a lista de favoritos
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp) // Ajuste para deixar espaço para a TopBar
            ) {
                items(favorites) { animal ->
                    // Layout do card individual
                    Card(
                        shape = RoundedCornerShape(30.dp),
                        elevation = 4.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .height(300.dp) // Tamanho ajustado do card
                    ) {
                        Box(
                            modifier = Modifier
                                .height(230.dp)
                                .width(320.dp)
                        ) {
                            // Imagem do animal que ocupa a metade inferior do Card
                            Image(
                                painter = rememberAsyncImagePainter(model = animal.ani_image),
                                contentDescription = animal.ani_name,
                                modifier = Modifier
                                    .height(230.dp)
                                    .width(320.dp)
                                    .clip(RoundedCornerShape(30.dp))
                                    .align(Alignment.BottomCenter) // Alinhamento no fundo
                                    .padding(bottom = 0.dp), // A imagem vai até o fundo
                                contentScale = ContentScale.Crop
                            )

                            // Nome do animal no topo com a data
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = animal.ani_name,
                                    fontSize = 20.sp,
                                    fontFamily = Poppins,
                                    fontWeight = MaterialTheme.typography.h6.fontWeight
                                )

                            }

                            // Imagem do corazon sobrepondo a parte inferior da imagem do animal
                            Image(
                                painter = painterResource(id = R.drawable.corazon2), // imagem do drawable
                                contentDescription = "Corazon",
                                modifier = Modifier
                                    .size(90.dp)
                                    .graphicsLayer(
                                        rotationZ = 15f,
                                    )
                                    .align(Alignment.BottomEnd)
                            )
                        }
                    }
                }
            }
        }
    }
}

// Função de carregamento dos favoritos
@RequiresApi(Build.VERSION_CODES.O)
fun fetchFavorites(
    context: Context,
    onSuccess: (List<Animal>) -> Unit,
    onError: (String) -> Unit
) {
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val token = sharedPreferences.getString("user_token", "") ?: ""

    if (token.isEmpty()) {
        onError("Token não encontrado.")
        return
    }

    val url = "http://10.0.2.2:8080/api/favorites/user"
    url.httpGet()
        .header("Authorization" to "Bearer $token")
        .responseJson { _, _, result ->
            when (result) {
                is Result.Success -> {
                    val jsonArray = result.value.array()
                    val animals = mutableListOf<Animal>()

                    for (i in 0 until jsonArray.length()) {
                        val jsonAnimal = jsonArray.getJSONObject(i)
                        val favDateString = jsonAnimal.optString("fav_date", "")

                        // Processa a data para um formato legível
                        val formattedDate = formatFavoriteDate(favDateString)

                        animals.add(
                            Animal(
                                ani_id = jsonAnimal.getInt("id"),
                                ani_name = jsonAnimal.getString("name"),
                                ani_breed = "", // Adapte conforme necessário
                                ani_birthday = "", // Adapte conforme necessário
                                ani_gender = "", // Adapte conforme necessário
                                ani_type = "", // Adapte conforme necessário
                                ani_image = jsonAnimal.optString("image", ""),
                                isFavorite = true,
                                favoriteDate = formattedDate, // Agora estamos passando a data formatada
                                ani_description = "" // Adapte conforme necessário
                            )
                        )
                    }

                    onSuccess(animals)
                }

                is Result.Failure -> {
                    val errorMsg = result.error.message ?: "Erro ao buscar favoritos."
                    onError(errorMsg)
                }
            }
        }
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatFavoriteDate(favDateString: String): String {
    return try {
        // Tenta converter a string de data para LocalDateTime
        val formatter = DateTimeFormatter.ISO_DATE_TIME
        val dateTime = LocalDateTime.parse(favDateString, formatter)

        // Calcula a diferença de tempo entre agora e a data de favoritação
        val now = LocalDateTime.now()
        val daysAgo = ChronoUnit.DAYS.between(dateTime, now)
        val minutesAgo = ChronoUnit.MINUTES.between(dateTime, now)

        // Exibe a diferença de tempo com base no tipo de dado
        when {
            daysAgo > 0 -> "$daysAgo dias atrás"
            minutesAgo > 0 -> "$minutesAgo minutos atrás"
            else -> "Agora"
        }
    } catch (e: Exception) {
        "Data inválida"
    }
}
