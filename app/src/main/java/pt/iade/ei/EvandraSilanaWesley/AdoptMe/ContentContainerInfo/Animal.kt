package pt.iade.ei.EvandraSilanaWesley.AdoptMe.ContentContainerInfo

import java.io.Serializable

data class Animal(
    val name: String,
    val description: String,
    var age: String,
    val gender: String,
    val imageResource: Int
)  : Serializable