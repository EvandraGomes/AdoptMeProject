package pt.iade.ei.EvandraSilanaWesley.AdoptMe.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.rememberAsyncImagePainter
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.Components.getAnimalList
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.Models.Animal
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ui.theme.Poppins


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavScreenContent(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF5E8D6))
                    .padding(paddingValues)
            ) {
                LazyColumn(modifier = Modifier.align(Alignment.Center)) {
                    items(getAnimalList().filter { it.isFavorite }) { animal ->
                        AnimalCardFav(animal)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    )
}

@Composable
fun AnimalCardFav(animal: Animal) {
    Card(
        modifier = Modifier
            .width(328.dp)
            .height(300.dp)
            .clip(RoundedCornerShape(50.dp)),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = Color(0xFFE7B070)
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Coloca o nome e a data do favorito no topo
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter) // Alinha o texto no topo
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = animal.ani_name,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 20.sp
                )
                Text(
                    text = " ${animal.favoriteDate}",
                    fontFamily = Poppins,
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 16.sp
                )
            }

            // Lista de imagens (URL ou drawable)
            val imageResources = listOfNotNull(
                animal.ani_image.takeIf { it.isNotEmpty() }, // Se a URL não estiver vazia, inclui a URL
                R.drawable.sem_foto.takeIf { animal.ani_image.isEmpty() } // Se não tiver URL, inclui o drawable
            )

            // Percorrer a lista de imagens e exibir as imagens
            imageResources.forEach { imageResource ->
                // Verifica se a imagem é drawable ou URL
                val painter = if (imageResource is Int) {
                    painterResource(id = imageResource) // Se for drawable
                } else {
                    rememberAsyncImagePainter(imageResource as String) // Se for URL
                }

                Image(
                    painter = painter,
                    contentDescription = animal.ani_name,
                    modifier = Modifier
                        .size(230.dp)
                        .height(100.dp)
                        .align(Alignment.BottomCenter) // Coloca a imagem na parte inferior
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop // Ajusta a imagem sem distorcer
                )
            }

            // Coração favorito
            Image(
                painter = painterResource(id = R.drawable.favheart),
                contentDescription = "Coração Favorito",
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.BottomEnd)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FavScreenPreview() {
    FavScreenContent(navController = rememberNavController())
}
