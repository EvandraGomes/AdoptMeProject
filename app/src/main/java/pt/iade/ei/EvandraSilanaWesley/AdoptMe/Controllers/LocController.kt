package pt.iade.ei.EvandraSilanaWesley.AdoptMe.Controllers

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.moshi.moshiDeserializerOf
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

// apenas pra loc da associacao

class LocController {

    private val moshi = Moshi.Builder().build()

    // Reutilizar o modelo DonationPoint pras locs
    fun fetchLocsByType(locType: String, onResult: (List<DonationPointsController.DonationPoint>) -> Unit, onError: (String) -> Unit) {
        val url = "http://10.0.2.2:8080/api/loc/type/$locType"
        val listType = Types.newParameterizedType(List::class.java, DonationPointsController.DonationPoint::class.java)
        val adapter = moshi.adapter<List<DonationPointsController.DonationPoint>>(listType)

        url.httpGet().responseObject(moshiDeserializerOf(adapter)) { _, _, result ->
            when (result) {
                is com.github.kittinunf.result.Result.Success -> onResult(result.get())
                is com.github.kittinunf.result.Result.Failure -> onError(result.error.message ?: "Erro desconhecido")
            }
        }
    }
}

