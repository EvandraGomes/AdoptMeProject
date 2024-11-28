package pt.iade.ei.EvandraSilanaWesley.AdoptMe.Components

import android.os.Build
import androidx.annotation.RequiresApi
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.testing.TestNavHostController
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.Models.Animal

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AnimalList(animals: List<Animal>) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        item {

            Row(modifier = Modifier.padding(bottom = 4.dp)){
                AnimalCategory("gato", R.drawable.gatocategory, onClick = {})
                AnimalCategory("cao", R.drawable.caocategory, onClick = {})
                AnimalCategory("passaro", R.drawable.avecategory, onClick = {})
                AnimalCategory("coelho", R.drawable.coelhocategory, onClick = {})
            }
        }

        items(animals) { animal ->
            // Passando o navController fictício para o preview
            val navController = TestNavHostController(LocalContext.current)
            AnimalCard(animal = animal, navController = navController)

        }

        }
    }


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun AnimalListPreview() {
    AnimalList( animals = listOf(
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
        )
    )
    )
}

