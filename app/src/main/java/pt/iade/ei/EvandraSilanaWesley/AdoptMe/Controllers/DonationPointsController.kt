package pt.iade.ei.EvandraSilanaWesley.AdoptMe.Controllers
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.moshi.moshiDeserializerOf
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.Types

class DonationPointsController {

    data class DonationPoint(
        @Json(name = "id") val locId: Long,
        @Json(name = "name") val locName: String,
        @Json(name = "type") val locType: String,
        @Json(name = "description") val locDescription: String,
        @Json(name = "latitude") val locLatitude: Double,
        @Json(name = "longitude") val locLongitude: Double
    )


    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val donationPointAdapter = moshi.adapter<List<DonationPoint>>(
        Types.newParameterizedType(List::class.java, DonationPoint::class.java)
    )

    fun fetchDonationPoints(onResult: (List<DonationPoint>) -> Unit, onError: (String) -> Unit) {
        val url = "http://10.0.2.2:8080/api/loc/donations"

        url.httpGet().responseObject(moshiDeserializerOf(donationPointAdapter)) { _, _, result ->
            when (result) {
                is com.github.kittinunf.result.Result.Success -> onResult(result.get())
                is com.github.kittinunf.result.Result.Failure -> onError(result.error.message ?: "Erro desconhecido")
            }
        }
    }
}
