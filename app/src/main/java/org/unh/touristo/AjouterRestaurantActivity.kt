package org.unh.touristo

import android.app.Activity
import android.graphics.BitmapFactory
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.unh.touristo.domaine.Destination
import org.unh.touristo.domaine.Restaurant
import org.unh.touristo.ui.theme.TouristoTheme
import org.unh.touristo.viewModel.DestinationViewModel
import org.unh.touristo.viewModel.RestaurantViewModel

class AjouterRestaurantActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TouristoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AjouterRestaurantScreen(back = { finish() })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AjouterRestaurantScreen( back: () -> Unit,
                             restaurantViewModel: RestaurantViewModel = RestaurantViewModel()
) {
    val name = remember { mutableStateOf("") }
    val namePlat = remember { mutableStateOf("") }
    val adresse = remember { mutableStateOf("") }
    val apercuImages = remember { mutableStateListOf<Image>() }
    val latitude = remember { mutableStateOf("") }
    val longetitude = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val lienRestaurant = remember { mutableStateOf("") }

    val context= LocalContext.current as Activity

    val contextResolver = LocalContext.current
    val image = remember { mutableStateOf<ImageBitmap?>(null) }
    val imageUri = remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            val bitmap = try {
                imageUri.value = it
                val inputStream = contextResolver.contentResolver.openInputStream(it!!)
                BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
                Toast.makeText(contextResolver, "Erreur $e", Toast.LENGTH_SHORT).show()
                null
            }

            image.value = bitmap?.asImageBitmap()
        },
    )



    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Ajouter les informations du restaurant",
            textAlign = TextAlign.Center) },
            navigationIcon = {
                IconButton(onClick = {context.finish()}) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription =null)
                }
            })
    }) {
        Column(modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(paddingValues = it)) {

            // Champ de saisie pour le nom
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text(text = "Nom du restaurant") },

                )

            Spacer(modifier = Modifier.height(8.dp))
            // Champ de saisie pour la présentation
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = namePlat.value,
                onValueChange = { namePlat.value = it },
                label = { Text(text = "Nom du plat") },
            )

            Spacer(modifier = Modifier.height(8.dp))
            // ADRESSE
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = adresse.value,
                onValueChange = { adresse.value = it },
                label = { Text(text = "Adresse") },
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = description.value,
                onValueChange = { description.value = it },
                label = { Text(text = "Description du restaurant") },
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = lienRestaurant.value,
                onValueChange = { lienRestaurant.value = it },
                label = { Text(text = "Lien vers le site web") },

                )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = latitude.value,
                onValueChange = { latitude.value = it },
                label = { Text(text = "Latitude") },

                )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = longetitude.value,
                onValueChange = { longetitude.value = it },
                label = { Text(text = "Longitude") },

                )



            Spacer(modifier = Modifier.height(16.dp))

            if (image.value != null) {
                androidx.compose.foundation.Image(
                    bitmap = image.value!!, contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(128.dp)
                        .clickable {
                            launcher.launch(PickVisualMediaRequest())
                        },
                )
            } else {
                Button(
                    modifier = Modifier
                        .width(300.dp)
                        .height(50.dp)
                        .padding(8.dp),

                    onClick = { launcher.launch(PickVisualMediaRequest()) },
                    shape = RoundedCornerShape(0.dp)
                )
                {
                    Text(text = "Ajouter une image ici",color = Color.Black)
                }
            }


            // Bouton pour enregistrer
            Button(
                onClick = {
            val restaurant = Restaurant(
                name = name.value,
                latitude = latitude.value,
                adresse = adresse.value,
                id = "",
                description = description.value,
                lienRestaurant = lienRestaurant.value,
                longetitude = longetitude.value,
                url = ""
            )
            imageUri.value?.let { it1 ->
                restaurantViewModel.addRestaurant(
                    it1,
                    restaurant
                ) {
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                    back()
                }
            }
        },
                        modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Enregistrer")
            }


        }
    }}


