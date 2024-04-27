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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.unh.touristo.domaine.Destination
import org.unh.touristo.ui.theme.TouristoTheme
import org.unh.touristo.viewModel.DestinationViewModel

class AjouterElementActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TouristoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AjouterDestinationScreen(back = { finish() })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AjouterDestinationScreen(
    back: () -> Unit,
    destinationViewModel: DestinationViewModel = DestinationViewModel()

) {
    val nom = remember { mutableStateOf("") }
    val type = remember { mutableStateOf("") }
    val adresse = remember { mutableStateOf("") }
    val presentation = remember { mutableStateOf("") }
    val apercuImages = remember { mutableStateListOf<Image>() }
    val activities = remember { mutableStateOf("") }
    val horairesOuverture = remember { mutableStateOf("") }
    val horairesFermeture = remember { mutableStateOf("") }
    val prixEntree = remember { mutableStateOf("") }
    val consignes = remember { mutableStateOf("") }
    val latitude = remember { mutableStateOf("") }
    val longitude = remember { mutableStateOf("") }
    val context = LocalContext.current as Activity

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
        TopAppBar(title = {
            Text(
                text = "Ajouter les informations de la destination",
                textAlign = TextAlign.Center
            )
        },
            navigationIcon = {
                IconButton(onClick = { context.finish() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
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
                value = nom.value,
                onValueChange = { nom.value = it },
                label = { Text(text = "Nom du lieu") },

                )

            Spacer(modifier = Modifier.height(8.dp))
            // Champ de saisie pour la présentation
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = presentation.value,
                onValueChange = { presentation.value = it },
                label = { Text(text = "Présentation") },
            )

            Spacer(modifier = Modifier.height(8.dp))
            // Champ de saisie pour le type
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = type.value,
                onValueChange = { type.value = it },
                label = { Text(text = "Type de lieu") },

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

            Spacer(modifier = Modifier.height(8.dp))
            // Champ de saisie pour la présentation

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = activities.value,
                onValueChange = { activities.value = it },
                label = { Text(text = "activities") },
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = horairesOuverture.value,
                onValueChange = { horairesOuverture.value = it },
                label = { Text(text = "horairesOuverture") },
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = horairesFermeture.value,
                onValueChange = { horairesFermeture.value = it },
                label = { Text(text = "horairesFermeture") },
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = prixEntree.value,
                onValueChange = { prixEntree.value = it },
                label = { Text(text = "prixEntree") },
            )

            Spacer(modifier = Modifier.height(16.dp))


            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = consignes.value,
                onValueChange = { consignes.value = it },
                label = { Text(text = "consignes") },
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
                value = longitude.value,
                onValueChange = { longitude.value = it },
                label = { Text(text = "Longitude") },

                )

            Spacer(modifier = Modifier.height(16.dp))

            if (image.value != null) {
                Image(
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
                    val destination = Destination(
                        name = nom.value,
                        type = type.value,
                        adresse = adresse.value,
                        id = "",
                        presentation = presentation.value,
                        activites = activities.value,
                        horairesOuverture = horairesOuverture.value,
                        horairesFermeture = horairesFermeture.value,
                        prixEntree = prixEntree.value,
                        consignes = consignes.value,
                        longitude = longitude.value,
                        latitude = latitude.value,
                        url = ""
                    )
                    imageUri.value?.let { it1 ->
                        destinationViewModel.addDestination(
                            it1,
                            destination
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
    }



}




