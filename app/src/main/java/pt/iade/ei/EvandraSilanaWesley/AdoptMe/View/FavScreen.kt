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

    // carregar os favs ao iniciar a tela
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
            backgroundColor = Color(0xFFF5E8D6),
            modifier = Modifier
                .height(0.dp)
        )

        if (errorMessage.isNotEmpty()) {

            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        } else {
            //  lista de favoritos
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp)
            ) {
                items(favorites) { animal ->

                    Card(
                        shape = RoundedCornerShape(30.dp),
                        elevation = 4.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .height(300.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .height(230.dp)
                                .width(320.dp)
                        ) {

                            Image(
                                painter = rememberAsyncImagePainter(model = animal.ani_image),
                                contentDescription = animal.ani_name,
                                modifier = Modifier
                                    .height(230.dp)
                                    .width(320.dp)
                                    .clip(RoundedCornerShape(30.dp))
                                    .align(Alignment.BottomCenter)
                                    .padding(bottom = 0.dp),
                                contentScale = ContentScale.Crop
                            )


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


                            Image(
                                painter = painterResource(id = R.drawable.corazon2),
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
                        val formattedDate = formatFavoriteDate(favDateString)

                        animals.add(
                            Animal(
                                ani_id = jsonAnimal.getInt("id"),
                                ani_name = jsonAnimal.getString("name"),
                                ani_breed = "", // n pede, n usa
                                ani_birthday = "",
                                ani_gender = "",
                                ani_type = "",
                                ani_image = jsonAnimal.optString("image", ""),
                                isFavorite = true,
                                favoriteDate = formattedDate,
                                ani_description = ""
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
        //  converter a string de data para LocalDateTime
        val formatter = DateTimeFormatter.ISO_DATE_TIME
        val dateTime = LocalDateTime.parse(favDateString, formatter)

        // ver a diferença de tempo de agora e a data de favoritação
        val now = LocalDateTime.now()
        val daysAgo = ChronoUnit.DAYS.between(dateTime, now)
        val minutesAgo = ChronoUnit.MINUTES.between(dateTime, now)

        when {
            daysAgo > 0 -> "$daysAgo dias atrás"
            minutesAgo > 0 -> "$minutesAgo minutos atrás"
            else -> "Agora"
        }
    } catch (e: Exception) {
        "Data inválida"
    }
}
