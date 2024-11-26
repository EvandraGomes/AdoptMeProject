package pt.iade.ei.EvandraSilanaWesley.AdoptMe.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.rememberAsyncImagePainter
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.Models.Animal
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ui.theme.Poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimalDescriptionScreenContent(navController: NavController,
                                   animal: Animal,
                                   onVoltarClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFF5E8D6))
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFF5E8D6),
                modifier = Modifier.height(64.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .height(130.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { /* Ação do botão Favoritar */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE7B070)
                        ),
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 8.dp)
                    ) {
                        Text("❤️", fontFamily = Poppins, color = Color.White)
                    }
                    Button(
                        onClick = { /* Ação do botão Adotar */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE7B070)
                        ),
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 8.dp)
                    ) {
                        Text("ADOTAR", fontFamily = Poppins, color = Color.White)
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .background(Color(0xFFF5E8D6))
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            // Seção das imagens (LazyRow)
            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(16.dp)
                        .background(Color(0xFFE7B070), shape = RoundedCornerShape(20.dp)),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(listOfNotNull(animal.ani_image.ifEmpty { null } ?: R.drawable.sem_foto)) {  imageResource ->
                        // Verifica se a imagem é drawable ou URL
                        val painter = if ( imageResource is Int) {
                            painterResource(id =  imageResource) // se for drawable
                        } else {
                            rememberAsyncImagePainter( imageResource as String) // se for URL
                        }

                        Image(
                            painter = painter,
                            contentDescription = "Foto do Animal",
                            modifier = Modifier
                                .size(260.dp)
                                .padding(8.dp)
                                .clip(RoundedCornerShape(20.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            // Detalhes do animal
            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text(
                        text = animal.ani_name,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = "Idade: ${animal.ani_age}",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black.copy(alpha = 0.7f),
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = "Raça: ${animal.ani_breed}",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black.copy(alpha = 0.7f),
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            // Descrição do animal
            item {
                Text(
                    text = animal.ani_description,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start,
                    fontFamily = Poppins,
                    color = Color.Black.copy(alpha = 0.7f),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimalDescriptionScreenContentPreview() {
    val animalReal = Animal(
        ani_id = 1,
        ani_name = "Akíra",
        ani_breed = "SRD",
        ani_age = "3 anos",
        ani_gender = "Feminino",
        ani_description = "Akíra é uma cadelinha muito amável.",
        imageResource = listOf(R.drawable.blackcat),
        ani_image = "", // URL vazia, é suposto usar drawable
        isFavorite = true,
        ani_type = "gato",
        favoriteDate = "2024-11-01"
    )

    AnimalDescriptionScreenContent(
        navController = rememberNavController(),
        animal = animalReal,
        onVoltarClick = { }
    )
}
