import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ui.theme.Poppins

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            WelcomeScreen(navController)
        }
    }
}

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5E8D6)) // Cor de fundo
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween // espaçamento do top ao bottom
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Welcome to\nAdoptMe!",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Poppins,
                    color = Color(0xFFE7B070),
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "O melhor amigo que procuras\nestá à espera de ti",
                    fontSize = 16.sp,
                    fontFamily = Poppins,
                    color = Color.Black,
                    textAlign = TextAlign.Start
                )
            }


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp), // Ajuste conforme necessário
                contentAlignment = Alignment.BottomCenter
            ) {
                // Imagem da calopsita
                Image(
                    painter = painterResource(id = R.drawable.calopsita),
                    contentDescription = "Calopsita",
                    modifier = Modifier
                        .size(80.dp)
                        .offset(x = (-26).dp, y = (287).dp)
                )
                // Imagem do gato
                Image(
                    painter = painterResource(id = R.drawable.gatito),
                    contentDescription = "Gato",
                    modifier = Modifier
                        .size(100.dp)
                        .offset(x = (-57).dp, y = (397).dp)
                )
                // Imagem do cachorro
                Image(
                    painter = painterResource(id = R.drawable.cachorito),
                    contentDescription = "Cachorro",
                    modifier = Modifier
                        .size(600.dp)
                        .offset(x = 35.dp, y = (355).dp)
                )
            }

            // Botão "Get Started"
            Button(
                onClick = { /* Navegar ou realizar ação */ },
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE7B070)),
                modifier = Modifier
                    .fillMaxWidth().padding(bottom = 50.dp)
                    .height(50.dp)
                    .padding(horizontal = 32.dp)
            ) {
                Text(
                    text = "Get Started",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Poppins
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(navController = rememberNavController())
}
