package pt.iade.ei.EvandraSilanaWesley.AdoptMe.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ui.theme.Poppins
import androidx.compose.material3.*
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogPostScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5E8D6))
    ) {
        // Top Bar
        TopAppBar(
            title = {
                Text(
                    text = "Blog Post",
                    style = TextStyle(
                        fontFamily = Poppins,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF4A4A4A)
            )
        )

        // Conteúdo principal com LazyColumn
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                Text(
                    text = "Resgatando Vidas: A História de Esperança da Associação Animal",
                    style = TextStyle(
                        fontFamily = Poppins,
                        fontSize = 24.sp,
                        color = Color(0xFF4A4A4A),
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            item {
                Text(
                    text = """
                        A Associação Animal surgiu com o intuito de resgatar animais abandonados e proporcionar um futuro melhor para aqueles 
                        que foram negligenciados pela sociedade. Em cada canto do nosso abrigo, vidas são resgatadas, tratadas e, o mais importante, 
                        amadas. Sabemos que a maior missão da nossa jornada é a de proporcionar aos animais não só cuidados, mas também a chance 
                        de encontrar um lar definitivo. 
                        
                        Recentemente, decidimos desenvolver um aplicativo que facilita a adoção de nossos resgatados e permite que as pessoas 
                        acompanhem em tempo real o progresso dos animais. A tecnologia vai ser um grande aliado em nossa luta, conectando aqueles 
                        que desejam ajudar, às histórias que precisam ser ouvidas.
                        
                        Nossa história é repleta de desafios, mas também de momentos de pura gratidão. Em cada resgate, sentimos o brilho nos olhos 
                        de um animal que encontra uma segunda chance. Acompanhe nossas histórias e venha fazer parte dessa corrente do bem.
                    """.trimIndent(),
                    style = TextStyle(
                        fontFamily = Poppins,
                        fontSize = 15.sp,
                        color = Color(0xFF4A4A4A),
                        lineHeight = 24.sp
                    ),
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }

            item {
                Image(
                    painter = painterResource(id = R.drawable.gibhli),
                    contentDescription = "Imagem de fundo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(bottom = 16.dp),
                    contentScale = ContentScale.Crop
                )
            }

            item {
                Text(
                    text = "Junte-se a nós na missão de salvar vidas!",
                    style = TextStyle(
                        fontFamily = Poppins,
                        fontSize = 20.sp,
                        color = Color(0xFF4A4A4A),
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            item {
                Text(
                    text = "",
                    style = TextStyle(
                        fontFamily = Poppins,
                        fontSize = 20.sp,
                        color = Color(0xFF4A4A4A),
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(bottom = 30.dp)
                )
            }

        }
    }
}
@Preview(showBackground = true)
@Composable
fun BlogPostScreenPreview() {
    val navController = rememberNavController()
    BlogPostScreen(navController = navController)
}
