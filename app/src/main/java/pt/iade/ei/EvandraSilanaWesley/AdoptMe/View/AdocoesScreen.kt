package pt.iade.ei.EvandraSilanaWesley.AdoptMe.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ui.theme.Poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdocoesScreenContent(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("HomeScreen") }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                }
                )

        }
    ) { innerPadding ->
        // Simulando postagens e comentários com dados fictícios
        val posts = listOf(
            "O processo de adoção foi muito bem detalhado! Um novo amigo felpudo faz parte da família agora. #Feliz #Adoção",
            "Acabaram de encontrar 4 filhotes fofinhos abandonados, já escolhi o meu😍",
            "Adotei um coelho, é tímido, mas adora um mimo!"
        )

        val comments = listOf(
            listOf("Eu achei um pouco burocratrico, mas tbm já tenho o meu!", "Aww, que fofura!"),
            listOf("\"Boa!\uD83D\uDC36\uD83D\uDC36\uD83D\uDC36\""),
            listOf("Fica calma, daqui a pouco mostra as verdadeiras cores 😂")
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            posts.forEachIndexed { index, post ->
                PostCard(post, comments[index])
                Spacer(modifier = Modifier.height(16.dp)) // Espaço entre postagens
            }
        }
    }
}

@Composable
fun PostCard(postText: String, comments: List<String>) {
    // Card de Post
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Título do Post
        Text(
            text = "Post",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = Poppins,
                color = Color.Gray
            )
        )

        // Texto do Post
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = postText,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 16.sp,
            fontFamily = Poppins,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Card de Comentários
        CommentCard(comments)
    }
}

@Composable
fun CommentCard(comments: List<String>) {
    // Card de Comentários
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xBFB6A27A))
            .padding(12.dp)
            .padding(bottom = 8.dp)
    ) {
        comments.forEach { comment ->
            Text(
                text = comment,
                fontFamily = Poppins,
                fontSize = 14.sp,

            )
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdocoesScreenContentPreview() {
    AdocoesScreenContent(navController = rememberNavController())
}
