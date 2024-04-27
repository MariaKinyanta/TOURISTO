package org.unh.touristo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import org.unh.touristo.repository.HistoriqueDao
import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.unh.touristo.ui.theme.TouristoTheme

class HistoriqueActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TouristoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HistoriqueScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoriqueScreen(  title: String = "Zoo de Lubumbashi",
                       date: String = "Vu le 27/04/2024",
                       time: String = "17H15",
                       title2: String="Park Hotel",
                       time2: String="17H16") {
    val museumImage = painterResource(id = R.drawable.zoo)
    val hebImage = painterResource(id = R.drawable.park)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Historique") },
                actions = {
                    IconButton(onClick = { /* TODO: Supprimer tout l'historique */ }) {
                        Icon(Icons.Filled.Delete, contentDescription = "Supprimer l'historique")
                    }
                }
            )
        },
        content = {
            Column(modifier = Modifier.padding(paddingValues=it)) {
                Text("Visites récentes", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    // Image redimensionnée et positionnée à gauche
                    Image(
                        painter = museumImage,
                        contentDescription = null,
                        modifier = Modifier
                            .width(50.dp) // Largeur réduite pour l'image
                            .height(50.dp) // Hauteur réduite pour l'image
                    )

                    // Contenu texte positionné à droite de l'image
                    Column(
                        modifier = Modifier
                            .fillMaxWidth() // Prend toute la largeur restante
                            .padding(16.dp) // Ajoute du padding autour du texte
                    ) {
                        Text(text = "$title", style = TextStyle(fontSize = 18.sp))
                        Text(text = "$date ", style = TextStyle(fontSize = 14.sp))
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    // Image redimensionnée et positionnée à gauche
                    Image(
                        painter = hebImage,
                        contentDescription = null,
                        modifier = Modifier
                            .width(50.dp) // Largeur réduite pour l'image
                            .height(50.dp) // Hauteur réduite pour l'image
                    )

                    // Contenu texte positionné à droite de l'image
                    Column(
                        modifier = Modifier
                            .fillMaxWidth() // Prend toute la largeur restante
                            .padding(16.dp) // Ajoute du padding autour du texte
                    ) {
                        Text(text = "$title2", style = TextStyle(fontSize = 18.sp))
                        Text(text = "$date ", style = TextStyle(fontSize = 14.sp))
                    }
                }



            }
        }
    )
}

@Composable
fun HistoryItem(title: String, date: String, time: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(title, style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.weight(1f))
        Column {
            Text(date, style = MaterialTheme.typography.bodySmall)
            Text(time, style = MaterialTheme.typography.bodySmall)
        }
    }
}


