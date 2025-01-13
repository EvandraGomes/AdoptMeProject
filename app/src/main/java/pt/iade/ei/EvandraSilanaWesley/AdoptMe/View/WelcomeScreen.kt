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
import androidx.compose.ui.layout.ContentScale
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

@Composable
fun WelcomeScreen(navController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.tela_inicial),
            contentDescription = "Imagem de fundo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // suppstamente reenderiza a imagem
        )
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
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 16.dp, top = 70.dp) // empurrar pra esquerda
            )
            Spacer(modifier = Modifier.height(12.dp))


            Text(
                text = "O melhor amigo que procuras\nestá à espera de ti",
                fontSize = 16.sp,
                fontFamily = Poppins,
                color = Color.Black,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 16.dp)
            )
        }


        Button(
            onClick = { navController.navigate("LoginScreen") },
            shape = RoundedCornerShape(27.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE7B070)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp)
                .height(60.dp)
                .padding(horizontal = 32.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = "Login",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins
            )
        }

    }
    }


@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(navController = rememberNavController())
}
