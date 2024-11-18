package pt.iade.ei.EvandraSilanaWesley.AdoptMe.ContentContainerInfo

import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ContentContainerInfo.Animal

@Composable
fun AnimalList(animals: List<Animal>) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {

            Row(modifier = Modifier.padding(bottom = 4.dp)){ AnimalCategory("Gatos", R.drawable.gatocategory)
                AnimalCategory("Caes", R.drawable.caocategory)
                AnimalCategory("Aves", R.drawable.avecategory)
                AnimalCategory("Coelhos", R.drawable.coelhocategory)
            }
        }
        items(animals) { animal ->

            AnimalCard(animal = animal)

        }

        }
    }

