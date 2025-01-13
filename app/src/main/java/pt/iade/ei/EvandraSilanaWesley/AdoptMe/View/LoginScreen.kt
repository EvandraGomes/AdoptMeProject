
package pt.iade.ei.EvandraSilanaWesley.AdoptMe.View

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.Controllers.LoginController
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ui.theme.Poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenContent(navController: NavHostController, context: Context) {
    val loginController = LoginController(context)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5E8D6))  // Cor de fundo para toda a tela
    ) {
        // Icone de seta por cima do fundo
        IconButton(
            onClick = { navController.navigateUp() },
            modifier = Modifier
                .padding(16.dp)
                .size(30.dp)// Posição do ícone
                .align(Alignment.TopStart)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Voltar"
            )
        }


        // Background image (imagem de fundo)
        Image(
            painter = painterResource(id = R.drawable.login_page),
            contentDescription = "Imagem de fundo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Conteúdo da tela (formulário de login)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Log in",
                fontSize = 28.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE7B070),
                modifier = Modifier.padding(bottom = 24.dp),
            )

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", fontFamily = Poppins) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color(0xFFE7B070),
                    unfocusedIndicatorColor = Color(0xFFE7B070),
                    containerColor = Color.Transparent
                )
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Senha", fontFamily = Poppins) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color(0xFFE7B070),
                    unfocusedIndicatorColor = Color(0xFFE7B070),
                    containerColor = Color.Transparent
                ),
                visualTransformation = PasswordVisualTransformation()
            )

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    modifier = Modifier.padding(vertical = 8.dp),
                    fontFamily = Poppins
                )
            }

            Button(
                onClick = {
                    isLoading = true
                    loginController.loginUser(
                        email = email,
                        password = password,
                        onResult = { token ->
                            isLoading = false
                            errorMessage = ""
                            Log.d("LoginToken", "Token recebido: $token")
                            navController.navigate("HomeScreen")
                        },
                        onError = { error ->
                            isLoading = false
                            errorMessage = error
                        }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE7B070)
                ),
                enabled = !isLoading
            ) {
                if (isLoading) {
                    Text(
                        "Carregando...",
                        fontFamily = Poppins,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                } else {
                    Text("Log in", fontFamily = Poppins, fontSize = 20.sp, color = Color.White)
                }
            }

            Text(
                text = "Ainda não tem conta? Cadastre-se",
                fontFamily = Poppins,
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { navController.navigate("SigninScreen") }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(bottom = 40.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Visitor icon and text
            Row(
                modifier = Modifier.clickable {
                    navController.navigate("VisitorScreen") // Navegação para tela de visitante
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Visitante",
                    tint = Color.DarkGray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Visitante",
                    fontFamily = Poppins,
                    fontSize = 20.sp,
                    color = Color.DarkGray
                )
            }

            // Google icon
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "Google",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    val context = LocalContext.current
    LoginScreenContent(navController = navController, context = context)
}

