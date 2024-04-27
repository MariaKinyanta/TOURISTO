package org.unh.touristo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.unh.touristo.login.LoginScreen
import org.unh.touristo.ui.theme.TouristoTheme
import org.unh.touristo.viewModel.DestinationViewModel

class AdminLieuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TouristoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ) {
                    AdminLieuScreen()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminLieuScreen(destinationViewModel: DestinationViewModel = DestinationViewModel()) {
    val destinationUiState by destinationViewModel.destinationUiState

    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Destination touristique", textAlign = TextAlign.Center) })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val intent = Intent(context, AjouterElementActivity()::class.java);
                context.startActivity(intent)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {
        Column(modifier = Modifier.padding(paddingValues = it)) {
            LazyColumn {
                items(destinationUiState.destinations) {
                    ListItem(
                        leadingContent = {
                            AsyncImage(
                                modifier = Modifier
                                    .size(70.dp)
                                    .clip(RoundedCornerShape(12.dp)),
                                model = it.url,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                            )
                        },
                        overlineContent = { Text(text = "${it.horairesOuverture} - ${it.horairesFermeture}") },
                        headlineContent = { Text(text = it.name) },
                        supportingContent = { Text(text = it.adresse) },
                        trailingContent = {
                            IconButton(onClick = { destinationViewModel.deleteDestination(it.id) }) {
                                Icon(imageVector = Icons.Rounded.Delete, contentDescription = null)
                            }
                        }
                    )
                }
            }
        }
    }
}
