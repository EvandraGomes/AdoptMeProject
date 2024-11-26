package pt.iade.ei.EvandraSilanaWesley.AdoptMe.Controllers

import android.R.id
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import org.json.JSONArray
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.Models.Animal

class AnimalCategoryController {

    fun fetchAnimalsByCategory(
        category: String,
        onResult: (List<Animal>) -> Unit,
        onError: (String) -> Unit
    ) {
        val url = "http://10.0.2.2:8080/api/type/$category"

        url.httpGet().responseJson { _, _, result ->
            when (result) {
                is Result.Success -> {
                    try {
                        val jsonArray = result.value.array()
                        val animals = parseAnimals(jsonArray)
                        onResult(animals)
                    } catch (e: Exception) {
                        onError("Erro ao processar os dados: ${e.localizedMessage}")
                    }
                }

                is Result.Failure -> {
                    onError("Erro na API: ${result.error.message ?: "Erro desconhecido"}")
                }
            }
        }
    }

    fun parseAnimals(jsonArray: JSONArray): List<Animal> {
        val animals = mutableListOf<Animal>()

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)

            val breed = jsonObject.getString("breed")
            val type = when {
                breed.contains("cao", ignoreCase = true) -> "cao"
                breed.contains("gato", ignoreCase = true) -> "gato"
                breed.contains("coelho", ignoreCase = true) -> "coelho"
                breed.contains("passsaro", ignoreCase = true) -> "passaro"
                else -> "Desconhecido"
            }

            val animal = Animal(
                ani_id = jsonObject.getInt("id"),
                ani_name = jsonObject.getString("name"),
                ani_breed = breed,
                ani_age = jsonObject.optString("age", "Desconhecida"),
                ani_gender = jsonObject.optString("gender", "Não informado"),
                imageResource = emptyList(),
                isFavorite = false,
                favoriteDate = "Não disponível",
                ani_description = jsonObject.optString("description", "Sem descrição"),
                ani_image = jsonObject.optString("imageUrl", ""),
                ani_type = type
            )

            animals.add(animal)
        }

        return animals
    }
}
