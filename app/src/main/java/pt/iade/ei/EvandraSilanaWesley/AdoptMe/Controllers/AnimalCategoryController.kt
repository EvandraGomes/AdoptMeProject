package pt.iade.ei.EvandraSilanaWesley.AdoptMe.Controllers

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import org.json.JSONArray
import org.json.JSONObject
import android.util.Log
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.Models.Animal

class AnimalCategoryController {

    fun fetchAnimalsByCategory(
        category: String,
        onResult: (List<Animal>) -> Unit,
        onError: (String) -> Unit
    ) {
        // 10.0.2.2 pra usar no emulador, IP do pc pra passar pro tlf
        val url = "http://10.0.2.2:8080/api/animals/type/$category"

        url.httpGet().responseJson { _, _, result ->
            when (result) {
                is Result.Success -> {
                    try {
                        // resposta bruta para "depuração"
                        val rawResponse = result.value.content
                        Log.d("API Response", "Resposta bruta: $rawResponse")

                        // ver se é um JSONArray ou JSONObject
                        val animals: List<Animal> = try {
                            val jsonArray = result.value.array()
                            Log.d("API Response", "Resposta é um JSONArray")
                            parseAnimals(jsonArray)
                        } catch (e: Exception) {
                            // Caso falhe como JSONArray, tenta como JSONObject
                            val jsonObject = result.value.obj()
                            Log.d("API Response", "Resposta é um JSONObject")
                            val jsonArray = jsonObject.getJSONArray("animals") // Ajuste se necessário
                            parseAnimals(jsonArray)
                        }

                        onResult(animals)
                    } catch (e: Exception) {
                        // Tratar erros ao processar a resposta
                        Log.e("API Error", "Erro ao processar os dados: ${e.localizedMessage}", e)
                        onError("Erro ao processar os dados: ${e.localizedMessage}")
                    }
                }

                is Result.Failure -> {
                    // caso de falha da API usar Logs
                    val errorMsg = result.error.message ?: "Erro desconhecido"
                    Log.e("API Error", "Erro na API: $errorMsg", result.error.exception)
                    onError("Erro na API: $errorMsg")
                }
            }
        }
    }

    //  converter um JSONArray pra uma lista de objetos
    fun parseAnimals(jsonArray: JSONArray): List<Animal> {
        val animals = mutableListOf<Animal>()

        for (i in 0 until jsonArray.length()) {
            try {
                val jsonObject = jsonArray.getJSONObject(i)

                val breed = jsonObject.optString("breed", "Desconhecida")
                val type = when {
                    breed.contains("cao", ignoreCase = true) -> "cao"
                    breed.contains("gato", ignoreCase = true) -> "gato"
                    breed.contains("coelho", ignoreCase = true) -> "coelho"
                    breed.contains("passsaro", ignoreCase = true) -> "passaro"
                    else -> "Desconhecido"
                }

                val animal = Animal(
                    ani_id = jsonObject.optInt("id", -1), // Usar valor padrão se o campo estiver ausente
                    ani_name = jsonObject.optString("name", "Sem nome"),
                    ani_breed = jsonObject.optString("breed", "Desconhecida"),
                    ani_birthday = jsonObject.optString("birthday", "Desconhecida"),
                    ani_gender = jsonObject.optString("gender", "Não informado"),
                    imageResource = emptyList(),
                    isFavorite = false,
                    favoriteDate = "Não disponível",
                    ani_description = jsonObject.optString("description", "Sem descrição"),
                    ani_image = jsonObject.optString("image", ""),
                    ani_type = type
                )

                animals.add(animal)
            } catch (e: Exception) {
                Log.e("Parse Error", "Erro ao processar o animal no índice $i: ${e.localizedMessage}", e)
            }
        }

        return animals
    }
}
