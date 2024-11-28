package pt.iade.ei.EvandraSilanaWesley.AdoptMe.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R

@Composable
fun LoginScreenContent(navController: NavHostController) {

    Box(
        modifier = Modifier
            .background(Color(0xFFF5E8D6))
            .fillMaxSize()

    ) {

        Image(
            painter = painterResource(id = R.drawable.login_page),
            contentDescription = "Imagem de fundo",
            modifier = Modifier
                .fillMaxWidth(),

            contentScale = ContentScale.Crop // suppstamente reenderiza a imagem
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenContentPreview() {
    LoginScreenContent(navController = rememberNavController())
}