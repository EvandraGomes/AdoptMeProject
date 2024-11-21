package pt.iade.ei.EvandraSilanaWesley.AdoptMe.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.Models.Animal
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.R
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ui.theme.Poppins

//Fazer apenas o cartão

@Composable
fun AnimalCard(animal: Animal) {
    Row(
        modifier = Modifier
            .fillMaxWidth(10.toFloat())
            .background(Color.White, shape = RoundedCornerShape(40.dp))
            .padding(0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = animal.imageResource.first()),
            contentDescription = "Imagem de ${animal.name}",
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(20.dp))
                .padding(end = 9.dp),
            contentScale = ContentScale.Crop
        )
        Column {
            Text(
                text = animal.name,
                fontSize = 18.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = animal.breed,
                fontSize = 14.sp,
                fontFamily = Poppins,
                color = Color.Gray
            )
            Text(
                text = animal.age,
                fontSize = 14.sp,
                fontFamily = Poppins,
                color = Color.Gray
            )
            Text(
                text = "Gênero: ${animal.gender}",
                fontSize = 14.sp,
                fontFamily = Poppins,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "mais detalhes...",
                fontSize = 14.sp,
                fontFamily = Poppins,
                color = Color(0xFFE7B070)
            )

        }
    }
}

//Realemnte o conteudo de cada animal

fun getAnimalList(): List<Animal> {
    return listOf(
        Animal(
            id = 1,
            name = "Pituco",
            breed = "periquito-australiano",
            age = "1 ano",
            gender = "M",
            type = "Aves",
            isFavorite = false,
            favoriteDate = "",
            imageUrl = "",
            description =
                    "    Personalidade e Comportamento:\n" +
                    "    O periquito apocalíptico é uma versão mais resistente e adaptada do periquito australiano. Ele desenvolveu uma personalidade mais forte e defensiva devido ao ambiente severo em que vive. É curioso, mas ao mesmo tempo cauteloso, sempre observando o ambiente antes de se aproximar de algo ou alguém. A interação com outros animais e seres humanos tende a ser seletiva, e ele pode ser territorial e protetor, principalmente se sentir ameaçado.\n" +
                    "\n" +
                    "    Cuidados Necessários:\n" +
                    "    Para cuidar de um periquito apocalíptico, é essencial criar um ambiente seguro e espaçoso. O pássaro precisa de bastante espaço para se mover e explorar, então uma gaiola grande é imprescindível. Além disso, é necessário fornecer uma dieta balanceada composta por sementes variadas, vegetais frescos e frutas, para garantir a nutrição adequada. Também é importante oferecer brinquedos e itens que estimulem sua mente, já que esse periquito precisa de desafios para se manter mentalmente ativo.\n" +
                    "\n" +
                    "    Saúde:\n" +
                    "    A saúde do periquito apocalíptico deve ser monitorada com atenção. Ele pode ser propenso a doenças respiratórias e deve ser mantido em um ambiente limpo e sem correntes de ar fortes. Além disso, sua alimentação deve ser monitorada para evitar problemas digestivos, e ele deve passar por check-ups regulares para detectar doenças comuns, como psitacose ou infecções parasitárias. A higiene é fundamental, e a gaiola deve ser limpa frequentemente para evitar problemas de saúde.\n" +
                    "\n" +
                    "    Compatibilidade com o Ambiente:\n" +
                    "    Embora o periquito apocalíptico seja mais resistente devido ao seu ambiente de origem, ele ainda precisa de um habitat controlado e protegido de mudanças climáticas drásticas. Ele é bem adaptado para viver em ambientes internos, mas se pode ser mantido em ambientes externos, deve ter um espaço seguro e estar protegido contra predadores. Sua resistência permite que ele sobreviva em condições desafiadoras, mas isso não significa que ele possa viver em qualquer ambiente sem riscos.\n" +
                    "\n" +
                    "    Compatibilidade com Outros Animais:\n" +
                    "    Este periquito pode viver bem com outros animais de pequeno porte, como outros periquitos ou aves de espécies similares, desde que a socialização seja feita de forma gradual. Porém, devido ao seu comportamento territorial, ele pode ser agressivo com outros periquitos ou animais que invadam seu espaço. É importante supervisionar interações com outros animais para evitar conflitos, e garantir que o periquito tenha seu espaço pessoal.\n" +
                    "\"\"\"\n",
            imageResource = listOf(
                R.drawable.pituco,
                R.drawable.pituco2,
                R.drawable.pituco3,
                )
        ),
        Animal(
            id = 2,
            name = "Biju",
            breed = "SRD",
            age = "8 meses",
            gender = "F",
            type = "Gatos",
            isFavorite = false,
            favoriteDate = "",
            description = "",
            imageUrl = "",
            imageResource =  listOf(R.drawable.biju)
        ),
        Animal(
            id = 3,
            name = "Pipoca",
            breed = "SRD",
            age = "5 meses",
            gender = "m",
            type = "Caes",
            isFavorite = false,
            favoriteDate = "",
            description = "",
            imageUrl = "",
            imageResource = listOf(R.drawable.pipoca)

        ),
        Animal(
            id = 4,
            name = "Sakura",
            breed = "SRD",
            age = "6 meses",
            gender = "F",
            type = "Gatos",
            isFavorite = true,
            favoriteDate = "Just now",
            description = "",
            imageUrl = "",
            imageResource = listOf(R.drawable.blackcat)
        ),
        Animal(
            id = 5,
            name = "Agatha",
            breed = "SRD",
            age = "3 meses",
            gender = "F",
            type = "Coelho",
            isFavorite = false,
            favoriteDate = "",
            description = "",
            imageUrl = "",
            imageResource = listOf(R.drawable.coelhito2)

        ),
        Animal(
            id = 6,
            name = "Akíra",
            breed = "SRD",
            age = "1 ano",
            gender = "M",
            type = "Caes",
            isFavorite = false,
            favoriteDate = "",
            description = "",
            imageUrl = "",
            imageResource = listOf(R.drawable.dogito)
        ),
        Animal(
            id = 7,
            name = "Caramelo",
            breed = "SRD",
            age = "8 meses",
            gender = "F",
            type = "Coelhos",
            isFavorite = true,
            favoriteDate = "Há uma hora",
            description = "",
            imageUrl = "",
            imageResource = listOf(R.drawable.coelhito)

        ),
    )
}
