package org.unh.touristo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.unh.touristo.domaine.Destination
import org.unh.touristo.domaine.Hebergement
import org.unh.touristo.ui.theme.TouristoTheme
import org.unh.touristo.viewModel.DestinationViewModel
import org.unh.touristo.viewModel.HebergementViewModel

class HebergementActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TouristoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HebergementScreen(viewModel = HebergementViewModel())
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HebergementScreen(viewModel: HebergementViewModel = HebergementViewModel()) {
    val hebergement by viewModel.hebergementUiState
    val context= LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Hébergement touristique", textAlign = TextAlign.Center) })
        },

        ) {
        Column(modifier = Modifier.padding(paddingValues = it)) {
            LazyColumn {
                items(hebergement.hebergements) {
                    HebergementListItem(hebergement = it){
                        val intent = Intent(context, ChambreActivity::class.java);
                        intent.putExtra("id", it)
                        context.startActivity(intent)
                    }
                }
            }
        }
    }
}


@Composable
fun HebergementListItem(hebergement: Hebergement, onclick:(String)->Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 4.dp)
        .clickable { onclick(hebergement.id) })
    {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(size = 12.dp)),

            contentScale = ContentScale.Crop,
            model = hebergement.url, contentDescription = null
        )
        Surface (Modifier.fillMaxWidth().align(Alignment.BottomCenter).padding(16.dp), color= Color.White.copy(alpha = 0.5f), shape = RoundedCornerShape(12.dp)) {
            Column (Modifier.fillMaxWidth().padding(4.dp)) {
                Text(text = hebergement.name, style = MaterialTheme.typography.bodyLarge)
                Text(text = hebergement.adresse)
            }
        }
    }
}

