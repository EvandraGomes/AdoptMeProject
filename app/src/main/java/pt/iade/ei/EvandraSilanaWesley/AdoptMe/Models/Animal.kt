package pt.iade.ei.EvandraSilanaWesley.AdoptMe.Models

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Animal(
    val ani_id: Int,
    val ani_name: String,
    val ani_breed: String,
    val ani_birthday: String,  // ex: "2022-12-20"
    val ani_gender: String,
    val ani_type: String,
    val ani_image: String = "",
    val imageResource: List<Int> = emptyList(),
    val isFavorite: Boolean = false,
    val favoriteDate: String = "",
    val ani_description: String = ""
) {

    // vai converter a string de aniversário para LocalDate
    @RequiresApi(Build.VERSION_CODES.O)
    fun getBirthdayAsLocalDate(): LocalDate {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return LocalDate.parse(ani_birthday, formatter)
    }

    // calcular a idade com base no aniversário
    @RequiresApi(Build.VERSION_CODES.O)
    fun calculateAge(): String {
        val birthday = getBirthdayAsLocalDate()
        val today = LocalDate.now()

        val ageInYears = today.year - birthday.year
        val ageInMonths = today.monthValue - birthday.monthValue
        val ageInDays = today.dayOfMonth - birthday.dayOfMonth

        return when {
            ageInYears > 0 -> {
                // Se for mais de 1 ano
                "$ageInYears ano${if (ageInYears > 1) "s" else ""}" +
                        (if (ageInMonths > 0 || ageInDays > 0) " e $ageInMonths mês${if (ageInMonths > 1) "es" else ""}" else "")
            }
            ageInMonths > 0 -> {
                // Se for mais de 1 mês
                "$ageInMonths mês${if (ageInMonths > 1) "es" else ""}" +
                        (if (ageInDays > 0) " e $ageInDays dia${if (ageInDays > 1) "s" else ""}" else "")
            }
            else -> {
                // Caso tenha menos de um mês
                "$ageInDays dia${if (ageInDays > 1) "s" else ""}"
            }
        }
    }
}


/* enquanto a API não está 100% pra outras telas que usam imagem do animal
permitir que se use tanto url como imageresource pra tela pelo menos abrir e ter algo decente
 */