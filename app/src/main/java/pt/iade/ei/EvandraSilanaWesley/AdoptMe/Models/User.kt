package pt.iade.ei.EvandraSilanaWesley.AdoptMe.Models

data class User(
    val name: String,
    val email: String,
    val password: String,
    val phone: String,
    val address: String,
    val birthdate: String
)

