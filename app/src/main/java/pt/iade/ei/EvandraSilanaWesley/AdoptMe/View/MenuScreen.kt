package pt.iade.ei.EvandraSilanaWesley.AdoptMe.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ui.theme.Poppins

@Composable
fun ItemComIcone(texto: String, iconeResId: Int, onClick: () -> Unit) {

    NavigationDrawerItem(
        icon = {
            Icon(
                painter = painterResource(id = iconeResId),
                contentDescription = texto,
                tint = Color.Black,
                modifier = Modifier.size(30.dp)
            )
        },
        label = {
            Text(
                texto,
                fontSize = 22.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
        },
        selected = false,
        onClick = onClick
    )
}

@Composable
fun MenuScreenContent(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(1.dp)
            .background(Color(0xFFF5E8D6))
    ) {
        Text(
            text = "AdoptMe",
            modifier = Modifier
                .padding(30.dp)
                .padding(bottom = 10.dp),
            fontSize = 40.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
        )

        Divider()


        // Itens de navegação com navegação para cada rota
        ItemComIcone("Adoção", R.drawable.adocao) {
        }

        ItemComIcone("Doação", R.drawable.doacoes) {
            navController.navigate("DoacoesScreen")
        }

        ItemComIcone("Favoritos", R.drawable.favoritos) {
        }

        ItemComIcone("Status", R.drawable.status) {
            navController.navigate("StatusScreen")
        }

        ItemComIcone("Marcações", R.drawable.marcacoes) {
            navController.navigate("MarcacoesScreen")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewTelaComItens() {
    val navController = rememberNavController()
    MenuScreenContent(navController = navController)
}
