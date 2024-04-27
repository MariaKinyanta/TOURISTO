package org.unh.touristo

import android.content.Intent
import android.os.Bundle
import android.view.Display
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import org.unh.touristo.ui.theme.TouristoTheme
import org.unh.touristo.viewModel.DestinationViewModel

class InformationDestinationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val id = intent.getStringExtra("id")
            TouristoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InformationDestinationScreen(id = id!!)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InformationDestinationScreen(
    destinationViewModel: DestinationViewModel = DestinationViewModel(),
    id: String
) {

    LaunchedEffect(Unit) {
        destinationViewModel.getId(id)
    }
    val informationUiState by destinationViewModel.destinationDetailsUiState
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Détails", textAlign = TextAlign.Center) })
        },

        ) {
        Column(modifier = Modifier.padding(paddingValues = it)) {
            LazyColumn {
                item {
                    if (informationUiState.destination != null) {
                        Card(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(text = "Présentation", fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(text = "${informationUiState.destination!!.presentation}")
                            }

                        }

                        Card(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(text = "Aperçu du lieu", fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(16.dp))
                                AsyncImage(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(250.dp)
                                        .clip(RoundedCornerShape(12.dp)),
                                    contentScale = ContentScale.Crop,
                                    model = informationUiState.destination!!.url,
                                    contentDescription = null
                                )
                            }

                        }

                        Card(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(text = "Activitées proposées", fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(text = "${informationUiState.destination!!.activites}")
                            }

                        }

                        Card(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(text = "Informations pratiques", fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(text = "Heure d'ouverture: ${informationUiState.destination!!.horairesOuverture}")
                                Text(text = "Heure de fermeture:${informationUiState.destination!!.horairesFermeture}")
                                Text(text = "${informationUiState.destination!!.prixEntree}")
                                Text(text = "${informationUiState.destination!!.consignes}")
                            }

                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                onClick = {
                                    val intent = Intent(context, RestaurantActivity::class.java);
                                    context.startActivity(intent)
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Voir les restaurants", fontSize = 10.sp)
                            }

                            Button(
                                onClick = {
                                    val intent = Intent(context, HebergementActivity::class.java);
                                    context.startActivity(intent)
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Voir les hébergements", fontSize = 10.sp)
                            }
                        }
                    }
                }


            }
        }
    }

}

