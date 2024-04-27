package org.unh.touristo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import org.unh.touristo.domaine.Destination
import org.unh.touristo.login.LoginViewModel
import org.unh.touristo.ui.theme.TouristoTheme
import org.unh.touristo.viewModel.DestinationViewModel

class DestinationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TouristoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DestinationScreen(viewModel = DestinationViewModel())
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationScreen(
    viewModel: DestinationViewModel = DestinationViewModel()
) {
    val destination by viewModel.destinationUiState
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Destination touristique", textAlign = TextAlign.Center) })
        },

        ) {
        Column(modifier = Modifier.padding(paddingValues = it)) {
            LazyColumn {
                items(destination.destinations) {
                    DestinationListItem(destination = it) {
                        val intent = Intent(context, InformationDestinationActivity()::class.java);
                        intent.putExtra("id", it)
                        context.startActivity(intent)
                    }
                }
            }
        }
    }
}

@Composable
fun DestinationListItem(destination: Destination, onclick: (String) -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 4.dp)
        .clickable { onclick(destination.id) }
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(size = 12.dp)),

            contentScale = ContentScale.Crop,
            model = destination.url, contentDescription = null
        )
        Surface(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            color = Color.White.copy(alpha = 0.5f),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp)) {
                Text(text = destination.name, style = MaterialTheme.typography.bodyLarge)
                Text(text = destination.type)
                Text(text = destination.adresse)
            }
        }
    }
}

