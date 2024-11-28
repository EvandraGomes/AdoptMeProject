package pt.iade.ei.EvandraSilanaWesley.AdoptMe.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R

@Composable
fun SigninScreenContent(navController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.cadastro_page),
            contentDescription = "Imagem de fundo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // suppstamente reenderiza a imagem
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SigninScreenContentPreview() {
    SigninScreenContent(navController = rememberNavController())
}