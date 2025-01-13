package pt.iade.ei.EvandraSilanaWesley.AdoptMe.View

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.XYTileSource
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.Controllers.DonationPointsController
import pt.iade.ei.EvandraSilanaWesley.AdoptMe.ui.theme.Poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoacoesScreenContent(navController: NavHostController) {
    var donationPoints by remember { mutableStateOf<List<DonationPointsController.DonationPoint>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    val donationPointsController = DonationPointsController()

    // Fazer a requisição para o backend
    LaunchedEffect(Unit) {
        donationPointsController.fetchDonationPoints(
            onResult = { points ->
                donationPoints = points
                Log.d("DoacoesScreenContent", "Pontos carregados: $points")
            },
            onError = { error ->
                errorMessage = error
                Log.e("DoacoesScreenContent", "Erro ao carregar pontos: $error")
            }
        )
    }

    Scaffold(
        topBar = { /* Removido o TopAppBar */ }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5E8D6))
                .padding(innerPadding)
        ) {
            if (errorMessage != null) {
                Text(
                    text = "Erro ao carregar pontos: $errorMessage",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                AndroidView(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f), // Garante que o mapa ocupe o espaço restante
                    factory = { ctx ->
                        // Configuração do mapa
                        Configuration.getInstance().load(ctx, ctx.getSharedPreferences("osm_pref", 0))
                        val mapView = MapView(ctx)

                        // Configurar o TileSource do OpenStreetMap diretamente
                        mapView.setTileSource(org.osmdroid.tileprovider.tilesource.TileSourceFactory.MAPNIK)

                        // Forçar a atualização do mapa
                        mapView.invalidate()

                        mapView.controller.setZoom(12.0) // Zoom inicial ajustado
                        mapView.controller.setCenter(GeoPoint(0.0, 0.0)) // Centro inicial neutro
                        mapView
                    },
                    update = { mapView ->
                        // Limpar marcadores anteriores
                        mapView.overlays.clear()

                        // Adicionar marcadores para os pontos de doação
                        donationPoints.forEach { point ->
                            val marker = Marker(mapView)
                            val geoPoint = GeoPoint(point.locLatitude, point.locLongitude)
                            marker.position = geoPoint
                            marker.title = point.locName
                            marker.snippet = point.locDescription

                            marker.setOnMarkerClickListener { _, _ ->
                                val gmmIntentUri = Uri.parse(
                                    "https://www.google.com/maps/dir/?api=1&destination=${point.locLatitude},${point.locLongitude}"
                                )
                                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                                mapIntent.setPackage("com.google.android.apps.maps")
                                context.startActivity(mapIntent)
                                true
                            }

                            mapView.overlays.add(marker)
                        }

                        // Centralizar no primeiro marcador, se existir
                        if (donationPoints.isNotEmpty()) {
                            val firstPoint = donationPoints[0]
                            mapView.controller.setCenter(GeoPoint(firstPoint.locLatitude, firstPoint.locLongitude))
                            mapView.controller.setZoom(15.0) // Zoom ajustado para marcadores
                        }
                    }
                )
            }


            // Botão "Operações Monetárias" com largura menor
            Button(
                onClick = { navController.navigate("DoacoesMonetariasScreen") },
                modifier = Modifier
                    .height(90.dp)
                    .padding(bottom = 30.dp)
                    .align(Alignment.CenterHorizontally)  // Centraliza o botão horizontalmente
                    .clip(RoundedCornerShape(7.dp)),  // Bordas arredondadas
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text("Doações Monetárias", color = Color.DarkGray, fontFamily = Poppins,  style = TextStyle(fontWeight = FontWeight.Bold))
            }
        }
    }
}




