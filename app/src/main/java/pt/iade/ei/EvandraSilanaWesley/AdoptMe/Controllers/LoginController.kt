package pt.iade.ei.EvandraSilanaWesley.AdoptMe.Controllers


import android.util.Log
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import org.json.JSONObject
import android.content.Context
import android.content.SharedPreferences

class LoginController(private val context: Context) {

    private val baseUrl = "http://10.0.2.2:8080/api/users"
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun loginUser(
        email: String,
        password: String,
        onResult: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        val url = "$baseUrl/login"
        val json = JSONObject().apply {
            put("email", email)
            put("password", password)
        }

        url.httpPost()
            .body(json.toString())
            .header("Content-Type" to "application/json")
            .responseJson { _, _, result ->
                when (result) {
                    is Result.Success -> {
                        val response = result.value.obj()
                        val token = response.optString("token", "")
                        if (token.isNotEmpty()) {
                            // salvaar o token no SharedPreferences
                            val editor = sharedPreferences.edit()
                            editor.putString("user_token", token) // Armazenando o token gerado
                            editor.apply()


                            onResult(token)
                        } else {
                            onError("Token não recebido.")
                        }
                    }

                    is Result.Failure -> {
                        val errorMsg = result.error.message ?: "Erro desconhecido"
                        onError(errorMsg)
                    }
                }
            }
    }
}
