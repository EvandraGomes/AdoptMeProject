package pt.iade.ei.EvandraSilanaWesley.AdoptMe.View

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.Controllers.SigninController
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.Models.User
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ui.theme.Poppins

@Composable
fun SigninScreenContent(navController: NavHostController) {
    val context = LocalContext.current // Context seguro do Compose
    val signinController = SigninController() // Agora controlador foi chamado


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Imagem de fundo
        Image(
            painter = painterResource(id = R.drawable.cadastro_page),
            contentDescription = "Imagem de fundo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Conteúdo da tela de cadastro
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val fields = listOf(
                "Nome" to "",
                "Email" to "",
                "Senha" to "",
                "Rua - Codigo Postal - Cidade" to "",
                "Telefone" to "",
                "Data de Nascimento" to ""
            )
            val textStates = fields.map { remember { mutableStateOf(it.second) } }

            fields.forEachIndexed { index, (label, _) ->
                CustomTextField(
                    value = textStates[index].value,
                    onValueChange = { textStates[index].value = it },
                    label = label,
                    isPassword = label == "Senha"
                )
            }

            Button(
                onClick = {
                    val userData = fields.mapIndexed { index, (label, _) ->
                        label to textStates[index].value
                    }.toMap()

                    // Verificar se todos os campos estão preenchidos
                    if (userData.values.any { it.isEmpty() }) {
                        Toast.makeText(context, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    // Criar objeto User
                    val user = User(
                        name = userData["Nome"] ?: "",
                        email = userData["Email"] ?: "",
                        password = userData["Senha"] ?: "",
                        phone = userData["Telefone"] ?: "",
                        address = userData["Rua - Codigo Postal - Cidade"] ?: "",
                        birthdate = userData["Data de Nascimento"] ?: ""
                    )

                    // Registrar usuário usando o controlador
                    signinController.registerUser(
                        user,
                        onResult = { message ->
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        },
                        onError = { error ->
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                        }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFE7B070).copy(alpha = 0.8f)
                )
            ) {
                Text(
                    text = "Criar conta",
                    color = Color.DarkGray,
                    fontSize = 16.sp,
                    fontFamily = Poppins,
                    style = MaterialTheme.typography.button
                )
            }
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontFamily = Poppins) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        shape = RoundedCornerShape(50.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color(0xFFE7B070).copy(alpha = 0.8f),
            focusedBorderColor = Color(0xFFE7B070),
            unfocusedBorderColor = Color(0xFFE7B070)
        ),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        textStyle = LocalTextStyle.current.copy(fontFamily = Poppins)
    )
}


@Preview(showBackground = true)
@Composable
fun SigninScreenContentPreview() {
    SigninScreenContent(navController = rememberNavController())
}