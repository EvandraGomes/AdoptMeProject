import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.compose.rememberNavController
import org.json.JSONObject
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.Controllers.ProfileController
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ui.theme.Poppins


@Composable
fun ProfileScreenContent(navController: NavController, context: Context) {
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val userToken = sharedPreferences.getString("user_token", null)

    // Verifica se o token existe
    if (userToken.isNullOrEmpty()) {
        LaunchedEffect(Unit) {
            navController.navigate("LoginScreen") {
                popUpTo("ProfileScreen") { inclusive = true }
            }
        }
        return
    }

    val profileController = ProfileController(context)

    // Variáveis para armazenar o estado dos dados do usuário
    var userData by remember { mutableStateOf<JSONObject?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Chama a função para buscar o perfil do usuário
    LaunchedEffect(Unit) {
        profileController.getUserProfile(
            onResult = { data ->
                userData = data
                errorMessage = null
            },
            onError = { error ->
                userData = null
                errorMessage = error
            }
        )
    }

    // Exibe os dados ou a mensagem de erro
    if (errorMessage != null) {
        Text(text = "Erro: $errorMessage")
    } else if (userData != null) {
        // Exibe os dados do usuário
        Text(text = "Nome: ${userData?.getString("name")}")
        Text(text = "Email: ${userData?.getString("email")}")
    } else {
        // Enquanto os dados não chegam, pode mostrar um loading
        CircularProgressIndicator()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
    ) {
        // Aqui você usa a imagem de fundo com um Box, a imagem agora ocupa toda a tela
        Image(
            painter = painterResource(id = R.drawable.wppprofile), // Altere para o nome correto da sua imagem
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 80.dp), // Ajuste o padding superior para não cobrir o conteúdo
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Imagem de Perfil
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color(0xFFD9D9D9), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Avatar",
                    tint = Color.Gray,
                    modifier = Modifier.size(100.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Exibe informações do usuário
            userData?.let {
                ProfileInfo(label = "Username", value = it.optString("name", "Desconhecido"))
                ProfileInfo(label = "E-mail", value = it.optString("email", "Desconhecido"))
                ProfileInfo(label = "Telefone", value = it.optString("phone", "Desconhecido"))
                ProfileInfo(label = "Nascimento", value = it.optString("birthdate", "Desconhecido"))
                ProfileInfo(label = "Morada", value = it.optString("address", "Desconhecido"))
            } ?: run {
                Text(
                    text = "A carregar os dados...",
                    fontFamily = Poppins,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botão de Logout
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Log Out",
                    fontSize = 18.sp,
                    fontFamily = Poppins,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.clickable { navController.navigate("LoginScreen") }
                )
            }
        }
    }
}

@Composable
fun ProfileInfo(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Text(
            text = "$label:",
            fontFamily = Poppins,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
        Text(
            text = value,
            fontFamily = Poppins,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(4.dp))
        Divider(color = Color.Gray, thickness = 1.dp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    val navController = rememberNavController()
    val context = LocalContext.current
    ProfileScreenContent(navController = navController, context = context)
}
