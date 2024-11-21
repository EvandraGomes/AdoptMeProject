package pt.iade.ei.EvandraSilanaWesley.AdoptMe.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R

// Set of Material typography styles to start with

val Poppins = FontFamily(
    Font(R.font.poppins_regular),  // Regular
    Font(R.font.poppins_bold, FontWeight.Bold) // Bold
)

val Alata = FontFamily(
    Font(R.font.alata_regular),
)

val Typography = androidx.compose.material3.Typography(
    displayLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    displayMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = Alata,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp
)
)


