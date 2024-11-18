package pt.iade.ei.EvandraSilanaWesley.AdoptMe.ContentContainerInfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R


@Composable
fun AnimalCategory(name: String, imageRes: Int) {
    Card(
        modifier = Modifier
            .padding(start = 10.dp)
            .size(80.dp), // ajustar o tamanho do quadrado
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Imagem de ${name}",
                modifier = Modifier
                    .size(50.dp) // tamanho da imagem
                    .align(Alignment.Center),
                contentScale = ContentScale.Fit
            )
        }

    }
}



    @Composable
    fun AnimalCategoryPreview() {
        Row {
            AnimalCategory("Gatos", R.drawable.gatocategory)
            AnimalCategory("Cães", R.drawable.caocategory)
            AnimalCategory("Aves", R.drawable.avecategory)
            AnimalCategory("Coelhos", R.drawable.coelhocategory)
        }
    }

