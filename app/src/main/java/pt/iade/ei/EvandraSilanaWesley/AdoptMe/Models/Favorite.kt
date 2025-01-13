package pt.iade.ei.EvandraSilanaWesley.AdoptMe.Models

data class Favorite(
    val id: Int,
    val userId: Int,
    val animalId: Int,
    val date: String,
    val userName: String,
    val animalName: String
)
