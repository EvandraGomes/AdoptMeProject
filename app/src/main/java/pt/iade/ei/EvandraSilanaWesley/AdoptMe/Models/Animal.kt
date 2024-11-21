package pt.iade.ei.EvandraSilanaWesley.AdoptMe.Models

import java.io.Serializable

data class Animal(
    val id: Int,
    val name: String,
    val breed: String,
    var age: String,
    val gender: String,
    val imageResource: List<Int>,
    val isFavorite: Boolean,
    val favoriteDate: String,
    val description: String,
    val imageUrl: String,

    val type: String // "Caes", "Gatos", "Aves", "Coelhos"
)  : Serializable