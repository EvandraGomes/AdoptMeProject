package pt.iade.ei.EvandraSilanaWesley.AdoptMe.Models

import java.io.Serializable

data class Animal(
    val ani_id: Int,
    val ani_name: String,
    val ani_breed: String,
    val ani_age: String,
    val ani_gender: String,
    val ani_type: String,
    val ani_image: String = "", // URL
    val imageResource: List<Int> = emptyList(), // imagens locais
    val isFavorite: Boolean = false,
    val favoriteDate: String = "",
    val ani_description: String = ""
)


/* enquanto a API não está 100% pra outras telas que usam imagem do animal
permitir que se use tanto url como imageresource pra tela pelo menos abrir e ter algo decente
 */