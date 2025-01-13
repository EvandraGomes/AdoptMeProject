package pt.iade.ei.EvandraSilanaWesley.AdoptMe.Controllers

import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import org.json.JSONObject
import android.util.Log
import androidx.compose.runtime.Composable
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.Models.User
import java.text.SimpleDateFormat
import java.util.Locale


class SigninController {

    // Função auxiliar para formatar a data
    private fun formatDate(inputDate: String): String? {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // Formato esperado pelo servidor
        return try {
            val date = inputFormat.parse(inputDate) // Tenta parsear a data
            inputFormat.format(date) // Retorna no formato desejado
        } catch (e: Exception) {
            e.printStackTrace()
            null // Retorna nulo se o formato estiver errado
        }
    }

    fun registerUser(
        user: User,
        onResult: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        val url = "http://10.0.2.2:8080/api/users"

        // Validar se todos os campos obrigatórios estão presentes
        if (user.name.isEmpty() || user.email.isEmpty() || user.password.isEmpty() || user.phone.isEmpty() || user.address.isEmpty()) {
            onError("Todos os campos são obrigatórios.")
            return
        }

        // Formatar a data antes de criar o JSON
        val formattedDate = formatDate(user.birthdate)

        // Validar se a data está no formato correto
        if (formattedDate == null) {
            onError("Data de nascimento inválida. Use o formato yyyy-MM-dd.")
            return
        }

        try {
            // Criar o JSON com os dados do usuário
            val json = JSONObject()
            json.put("name", user.name)
            json.put("email", user.email)
            json.put("password", user.password)
            json.put("phone", user.phone)
            json.put("address", user.address)  // O nome do campo no banco de dados
            json.put("birthdate", formattedDate)  // A data já está formatada corretamente

            // Log para verificar o JSON antes de envia
            Log.d("JSON Enviado", json.toString())

            // Enviar requisição para a API
            url.httpPost()
                .body(json.toString())
                .header("Content-Type" to "application/json")
                .responseJson { _, _, result ->
                    when (result) {
                        is Result.Success -> {
                            val response = result.value.obj()
                            val message = response.optString("message", "Usuário registrado com sucesso.")
                            Log.d("API Success", "Resposta: $message")
                            onResult(message)
                        }
                        is Result.Failure -> {
                            val errorMsg = result.error.message ?: "Erro desconhecido"
                            Log.e("API Error", "Erro ao registrar usuário: $errorMsg")
                            onError("Erro ao registrar usuário: $errorMsg")
                        }
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
            onError("Erro ao construir a requisição: ${e.message}")
        }
    }
}
