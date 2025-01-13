package pt.iade.ei.EvandraSilanaWesley.AdoptMe.View

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Divider
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
            .padding(1.dp, top = 30.dp)
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

        // Outros itens de navegação aqui
        ItemComIcone(
            texto = "Doação", iconeResId = R.drawable.doacoes
        ) {
            navController.navigate("DoacoesScreen")
        }

        ItemComIcone(
            texto = "Favoritos", iconeResId = R.drawable.favoritos
        ) {
            navController.navigate("FavScreen")
        }

        ItemComIcone(
            texto = "Status", iconeResId = R.drawable.status
        ) {
            navController.navigate("StatusScreen")
        }


        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "About Us",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .padding(bottom = 52.dp)
                .clickable {
                    navController.navigate("BlogPostScreen")
                },
            fontSize = 18.sp,
            fontFamily = Poppins,
            color = Color(0xFF4A4A4A)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTelaComItens() {
    val navController = rememberNavController()
    MenuScreenContent(navController = navController)
}
