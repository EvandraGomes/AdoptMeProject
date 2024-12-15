
package pt.iade.ei.EvandraSilanaWesley.AdoptMe.Components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.testing.TestNavHostController
import coil.compose.rememberAsyncImagePainter
import coil.compose.AsyncImagePainter
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.Models.Animal
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ui.theme.Poppins


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AnimalCard(animal: Animal, navController: NavController) {
    Row(
        modifier = Modifier
            .clickable {
                // navega para a tela de descrição pelo o ID do animal
                navController.navigate("AnimalDescriptionScreen/${animal.ani_id}")
            }
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(40.dp))
            .padding(0.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {
        var imagePainter by remember { mutableStateOf<Painter?>(null) }
        imagePainter = if (animal.imageResource.isNotEmpty()) {
            // Use o drawable local como fallback
            painterResource(id = animal.imageResource.first())
        } else {
            painterResource(id = R.drawable.sem_foto)
        }

        val painterAsync = rememberAsyncImagePainter(
            model = animal.ani_image,
            onSuccess = {
                imagePainter = it.painter
            },
            onError = {
                Log.e("AnimalCard Image", "Failed to load image from server for animal ${animal.ani_id} with URL ${animal.ani_image}")
            }
        )

        Image(
            painter = painterAsync,
            contentDescription = "Imagem de ${animal.ani_name}",
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(20.dp))
                .padding(end = 9.dp),
            contentScale = ContentScale.Crop
        )

        Column {
            Text(
                text = animal.ani_name,
                fontSize = 18.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = animal.ani_breed,
                fontSize = 14.sp,
                fontFamily = Poppins,
                color = Color.Gray
            )
            Text(
                text = animal.calculateAge(),  // Exibe a idade calculada dinamicamente
                fontSize = 14.sp,
                fontFamily = Poppins,
                color = Color.Gray
            )
            Text(
                text = "Gênero: ${animal.ani_gender}",
                fontSize = 14.sp,
                fontFamily = Poppins,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "mais detalhes...",
                fontSize = 14.sp,
                fontFamily = Poppins,
                color = Color(0xFFE7B070),
            )
        }
    }
}

fun getAnimalList(): List<Animal> {
    return listOf(
        Animal(
            ani_id = 1,
            ani_name = "Agatha",
            ani_breed = "SRD",
            ani_birthday = "2024-09-08",
            ani_gender = "F",
            ani_type = "coelho",
            ani_image = "", // Sem URL
            imageResource = listOf(R.drawable.coelhito2),
            isFavorite = true,
            favoriteDate = "há uma hora",
            ani_description = "Coelha tímida, não gosta de água."
        ),

        Animal(
            ani_id = 2,
            ani_name = "Akíra",
            ani_breed = "SRD",
            ani_birthday = "1 ano",
            ani_gender = "F",
            ani_type = "gato",
            ani_image = "", // Sem URL
            favoriteDate = "Ontem",
            imageResource = listOf(R.drawable.blackcat),
            isFavorite = true,
            ani_description = "Muito leal e amorosa."
        ),

        )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun AnimalCardPreview() {
    // Criação de um NavController Fake para o Preview
    val fakeNavController = TestNavHostController(LocalContext.current)

    // Exemplo de animal para o preview
    val exampleAnimal = Animal(
        ani_id = 2,
        ani_name = "Akíra",
        ani_breed = "SRD",
        ani_birthday = "2024-11-10",  // Data de nascimento
        ani_gender = "F",
        ani_type = "gato",
        ani_image = "", // Sem URL
        favoriteDate = "Ontem",
        imageResource = listOf(R.drawable.blackcat),
        isFavorite = true,
        ani_description = "Muito leal e amorosa."
    )

    AnimalCard(animal = exampleAnimal, navController = fakeNavController)
}
