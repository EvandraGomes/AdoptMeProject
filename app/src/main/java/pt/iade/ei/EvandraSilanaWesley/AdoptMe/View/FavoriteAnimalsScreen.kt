package pt.iade.ei.EvandraSilanaWesley.AdoptMe.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R

@Composable
fun AnimalCard(
    animalName: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp),
        shape = RoundedCornerShape(16.dp),

    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(200.dp)
                .background(Color(0xFFE1BEE7))
                .clip(RoundedCornerShape(66.dp))
                .padding(top = 40.dp)
        ) {

            Text(
                text = animalName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.TopCenter) // centraliza o nome do animal na Box
                    .padding(bottom = 16.dp)
            )


            Icon(
                painter = painterResource(id = R.drawable.corazon2),
                contentDescription = "corazon",
                modifier = Modifier
                    .align(Alignment.TopEnd) // posicionar o ícone no canto superior direito
                    .padding(top = 8.dp, end = 16.dp)
                    .size(32.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimalCardExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AnimalCard(animalName = "Silana")
        AnimalCard(animalName = "Biju")
        AnimalCard(animalName = "Pituco")
    }
}
