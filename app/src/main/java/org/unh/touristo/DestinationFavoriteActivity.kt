package org.unh.touristo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.unh.touristo.ui.theme.TouristoTheme

class DestinationFavoriteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TouristoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DestinationFavoriteScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationFavoriteScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Destinations favorites", textAlign = TextAlign.Center) }
            )
        }
    ) {
        Box( // Ensures content is centered within the Scaffold
            modifier = Modifier.fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues = it),
            contentAlignment = Alignment.Center
        ) {
            Column( // Vertical arrangement of elements
                modifier = Modifier.padding(16.dp) // Add some padding
            ) {
                Text(
                    "Aucune destination favorite pour l'instant",
                    style = TextStyle(fontSize = 20.sp) // Use sp for font size
                )

            }
        }
    }
}


