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
        val url = "http://172.17.80.1:8080/api/animals"

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

            // Inferir tipo com base no nome ou raça
            val breed = jsonObject.getString("breed")
            val type = when {
                breed.contains("Caes", ignoreCase = true) -> "Cão"
                breed.contains("Gatos", ignoreCase = true) -> "Gato"
                breed.contains("Coelhos", ignoreCase = true) -> "Coelho"
                breed.contains("aves", ignoreCase = true) -> "Aves"
                else -> "Desconhecido"
            }

            val animal = Animal(
                id = jsonObject.getInt("id"),
                name = jsonObject.getString("name"),
                breed = breed,
                age = jsonObject.optString("age", "Desconhecida"),
                gender = jsonObject.optString("gender", "Não informado"),
                imageResource = emptyList(),  // Lista vazia para imageResource
                isFavorite = false,  // Valor padrão para isFavorite
                favoriteDate = "Não disponível",  // Data padrão
                description = jsonObject.optString("description", "Sem descrição"),
                imageUrl = jsonObject.optString("imageUrl", ""),
                type = type  // Tipo inferido com base na raça
            )

            animals.add(animal)
        }

        return animals
    }
}