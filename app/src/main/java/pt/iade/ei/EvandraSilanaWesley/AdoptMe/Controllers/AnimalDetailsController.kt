package pt.iade.ei.EvandraSilanaWesley.AdoptMe.Controllers

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.Models.Animal

class AnimalDetailsController {

    suspend fun fetchAnimalDetails(id: Int): Animal? {
        val url = "http://10.0.2.2:8080/api/animals/$id"
        return try {
            withContext(Dispatchers.IO) {
                val (request, response, result) = url.httpGet().responseString()

                when (result) {
                    is Result.Success -> {
                        val json = JSONObject(result.get())
                        Animal(
                            ani_id = json.getInt("id"),
                            ani_name = json.getString("name"),
                            ani_breed = json.getString("breed"),
                            ani_birthday = json.getString("birthday"),
                            ani_gender = json.getString("gender"),
                            ani_type = json.getString("type"),
                            ani_image = json.getString("image"),
                            ani_description = json.getString("description"),
                            isFavorite = false
                        )
                    }
                    is Result.Failure -> null
                }
            }
        } catch (e: Exception) {
            null
        }
    }
}
