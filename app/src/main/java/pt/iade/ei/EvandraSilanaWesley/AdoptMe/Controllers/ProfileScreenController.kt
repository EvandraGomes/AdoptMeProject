
package pt.iade.ei.EvandraSilanaWesley.AdoptMe.Controllers

import android.content.Context
import android.content.SharedPreferences
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.json.responseJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class ProfileController(private val context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    //  pegar os dados do perfil do user
    fun getUserProfile(onResult: (JSONObject) -> Unit, onError: (String) -> Unit) {
        val userToken = sharedPreferences.getString("user_token", null)

        // confrimar se o token existe
        if (!userToken.isNullOrEmpty()) {
            // Faz a requisição para buscar o perfil do usuário com o token
            Fuel.get("http://10.0.2.2:8080/api/users/auth/user")
                .header("Authorization" to "Bearer $userToken") // Incluindo o token no cabeçalho
                .responseJson { _, _, result ->
                    result.fold(
                        success = { response ->
                            val userData = response.obj() // dados do usuário
                            onResult(userData) // passar os dados para o callback
                        },
                        failure = { error ->
                            onError("Erro ao buscar dados do usuário: ${error.message}")
                        }
                    )
                }
        } else {
            onError("Token de autenticação não encontrado")
        }
    }
}
