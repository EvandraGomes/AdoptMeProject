

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.clickable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ui.theme.Poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenContent(navController: NavHostController) {
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
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF5E8D6)
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF5E8D6))
                .padding(horizontal = 24.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Imagem de Perfil
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color(0xFFD9D9D9), CircleShape)
                    .clickable {

                    },
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

            // Campos de texto
            ProfileTextField(label = "Username")
            Divider(modifier = Modifier.padding(vertical = 1.dp))

            ProfileTextField(label = "E-mail", keyboardType = KeyboardType.Email)
            Divider(modifier = Modifier.padding(vertical = 1.dp))

            ProfileTextField(label = "Telefone", keyboardType = KeyboardType.Phone)
            Divider(modifier = Modifier.padding(vertical = 1.dp))

            ProfileTextField(label = "Nascimento")
            Divider(modifier = Modifier.padding(vertical = 1.dp))

            ProfileTextField(label = "Morada")
            Divider(modifier = Modifier.padding(vertical = 1.dp))

            Spacer(modifier = Modifier.height(24.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.logout),
                    contentDescription = "Logout",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Log Out",
                    fontSize = 16.sp,
                    fontFamily = Poppins,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.White, shape = RoundedCornerShape(10.dp))
                    .clickable { /* para o editar perfil */ }
            ) {
                Text(
                    text = "Edit Profile", // Texto
                    modifier = Modifier
                        .align(Alignment.Center) // Alinha o texto no centro
                        .padding(16.dp),
                    fontSize = 18.sp, // Tamanho da fonte
                    fontFamily = Poppins,
                    color = Color.Gray
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTextField(label: String, keyboardType: KeyboardType = KeyboardType.Text) {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        label = {
            Text(
                text = label,
                fontSize = 17.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .border(BorderStroke(1.dp, Color.Transparent)), // removemos as bordas
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Transparent,  // removemos a borda ao focar
            unfocusedBorderColor = Color.Transparent // removemos a borda ao não focar
        ),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        singleLine = true
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    val navController = rememberNavController()
    ProfileScreenContent(navController = navController)
}
