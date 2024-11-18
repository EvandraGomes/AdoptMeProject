package pt.iade.ei.EvandraSilanaWesley.AdoptMe.ContentContainerInfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ContentContainerInfo.Animal
import androidx.compose.foundation.clickable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ui.theme.Poppins

//Fazer apenas o cartão

@Composable
fun AnimalCard(animal: Animal) {
    Row(
        modifier = Modifier
            .fillMaxWidth(10.toFloat())
            .background(Color.White, shape = RoundedCornerShape(40.dp))
            .padding(0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = animal.imageResource),
            contentDescription = "Imagem de ${animal.name}",
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(20.dp))
                .padding(end = 9.dp),
            contentScale = ContentScale.Crop
        )
        Column {
            Text(
                text = animal.name,
                fontSize = 18.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = animal.description,
                fontSize = 14.sp,
                fontFamily = Poppins,
                color = Color.Gray
            )
            Text(
                text = animal.age,
                fontSize = 14.sp,
                fontFamily = Poppins,
                color = Color.Gray
            )
            Text(
                text = "Gênero: ${animal.gender}",
                fontSize = 14.sp,
                fontFamily = Poppins,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "mais detalhes...",
                fontSize = 14.sp,
                fontFamily = Poppins,
                color = Color(0xFFE7B070)
            )

        }
    }
}

//Realemnte o conteudo de cada animal

fun getAnimalList(): List<Animal> {
    return listOf(
        Animal(
            name = "Pituco",
            description = "periquito-australiano",
            age = "1 ano",
            gender = "M",
            imageResource = R.drawable.pituco
        ),
        Animal(
            name = "Biju",
            description = "SRD",
            age = "8 meses",
            gender = "F",
            imageResource = R.drawable.biju
        ),
        Animal(
            name = "Pipoca",
            description = "SRD",
            age = "5 meses",
            gender = "m",
            imageResource = R.drawable.pipoca

        ),Animal(
            name = "Pipoca",
            description = "SRD",
            age = "5 meses",
            gender = "M",
            imageResource = R.drawable.pipoca

        ),Animal(
            name = "Pipoca",
            description = "SRD",
            age = "5 meses",
            gender = "M",
            imageResource = R.drawable.pipoca

        ),Animal(
            name = "Pipoca",
            description = "SRD",
            age = "5 meses",
            gender = "M",
            imageResource = R.drawable.pipoca

        ),
    )
}
