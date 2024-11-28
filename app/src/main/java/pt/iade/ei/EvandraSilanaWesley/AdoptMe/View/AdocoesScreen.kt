package pt.iade.ei.EvandraSilanaWesley.AdoptMe.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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


//CODIGO
@Composable
fun AdocoesScreenContent(navController: NavHostController) {

    Box(
        modifier = Modifier
            .background(Color(0xFFF5E8D6))
            .fillMaxSize()

    )

    // Simulando postagens e comentários com dados fictícios
    val posts = listOf(
        "Adoção incrível! Meu novo amigo peludo chegou em casa. #Feliz #Adoção",
        "Foi muito divertido adotar o Timmy, ele é tão fofo! 😍",
        "Adotei um coelho, ele é muito bagunceiro, mas é a coisa mais fofa!",
    )

    val comments = listOf(
        listOf("Que legal! Como ele está se comportando?", "Aww, que fofura!"),
        listOf("Que legal! O Timmy parece ótimo! 🐶", "Adorei o seu post!"),
        listOf("Ah, coelhos são uma bagunça, mas tão divertidos! 😂"),
    )

    Column(
        modifier = Modifier

            .fillMaxSize()
            .padding(16.dp)
    ) {
        posts.forEachIndexed { index, post ->
            PostWithComments(post, comments[index])
            Spacer(modifier = Modifier.height(16.dp)) // Espaço entre postagens
        }
    }
}

@Composable
fun PostWithComments(postText: String, comments: List<String>) {
    // Caixa de Post
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .size(200.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
            .shadow(4.dp, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))

    ) {
        // Texto do Post
        Text(
            text = postText,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        // Caixa de Comentários
        Spacer(modifier = Modifier.height(12.dp)) // Espaço entre o post e os comentários
        comments.forEach { comment ->
            CommentBox(comment)
            Spacer(modifier = Modifier.height(8.dp)) // Espaço entre os comentários
        }

        // Caixa de Inserir Novo Comentário
        AddCommentBox()
    }
}

@Composable
fun CommentBox(comment: String) {
    // Caixa de Comentário
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Text(
            text = comment,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

@Composable
fun AddCommentBox() {
    // Caixa para inserir um novo comentário
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Text(
            text = "Adicionar comentário...",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AdocoesScreenContentPreview() {
    AdocoesScreenContent(navController = rememberNavController())
}