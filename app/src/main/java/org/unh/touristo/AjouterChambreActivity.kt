//package org.unh.touristo
//
//import android.app.Activity
//import android.graphics.BitmapFactory
//import android.media.Image
//import android.os.Bundle
//import android.util.Log
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.compose.setContent
//import androidx.activity.result.PickVisualMediaRequest
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material3.Button
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.State
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.ImageBitmap
//import androidx.compose.ui.graphics.asImageBitmap
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.ktx.Firebase
//import org.unh.touristo.domaine.Chambre
//import org.unh.touristo.ui.theme.TouristoTheme
//
//class AjouterChambreActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            TouristoTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ){
//                AjouterChambreScreen(
//                    name = "Numéro de la chambre",  // Provide your name
//                    modifier = Modifier,  // Optional modifier for styling (usually not needed)
//                    roomsState = remember { mutableStateOf(mutableListOf<Chambre>()) },  // Create an initial empty list for rooms
//                    updateRooms = { rooms: MutableList<Chambre>, newNumber: Int ->
//                        // Implement your logic to update rooms based on newNumber
//                    }  // Function to update the list of rooms
//                )
//                }
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AjouterChambreScreen(
//    name: String,
//    modifier: Modifier = Modifier,
//    roomsState: State<MutableList<Chambre>>,
//    updateRooms: (MutableList<Chambre>, Int) -> Unit
//) {
//    val db = FirebaseFirestore.getInstance()
//    val number = remember { mutableStateOf("") } // Initially empty number of rooms
//    val adresse = remember { mutableStateOf("") } // Initially empty address
//    val apercuImages = remember { mutableStateListOf<ImageBitmap?>() } // List for ImageBitmaps
//
//    val context = LocalContext.current as Activity // Get the current activity context
//
//    // No need to create an instance of UpdateRooms within Greeting2
//    val rooms = remember { mutableStateListOf<Chambre>() } // Liste pour contenir les chambres récupérées
//
//    val contextResolver = LocalContext.current
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.PickMultipleVisualMedia(), // Use PickMultipleVisualMedia
//        onResult = {
//            val bitmaps = mutableListOf<ImageBitmap?>()
//            it.forEach { uri ->
//                try {
//                    val inputStream = contextResolver.contentResolver.openInputStream(uri)
//                    val bitmap = BitmapFactory.decodeStream(inputStream)
//                    bitmaps.add(bitmap?.asImageBitmap())
//                } catch (e: Exception) {
//                    Toast.makeText(contextResolver, "Erreur $e", Toast.LENGTH_SHORT).show()
//                }
//            }
//            apercuImages.addAll(bitmaps) // Add all selected images to the list
//        }
//    )
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Ajouter les informations des chambres", textAlign = TextAlign.Center) },
//                navigationIcon = {
//                    IconButton(onClick = { context.finish() }) {
//                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
//                    }
//                }
//            )
//        }
//    ) {
//        Column(modifier = Modifier.padding(paddingValues = it)) {
//
//            Spacer(modifier = Modifier.height(40.dp))
//            LaunchedEffect(Unit) {
//                // Accéder à la référence de la base de données Firebase Realtime Database
//                val database = Firebase.database.reference.child("chambres")
//
//                database.addValueEventListener(object : ValueEventListener {
//                    override fun onDataChange(snapshot: DataSnapshot) {
//                        rooms.clear()
//                        if (snapshot.exists()) {
//                            for (child in snapshot.children) {
//                                val roomInfo = child.getValue(RoomInfos::class.java)
//                                if (roomInfo != null) {
//                                    rooms.add(roomInfo)
//                                }
//                            }
//                        }
//                    }
//
//                    override fun onCancelled(error: DatabaseError) {
//                        Log.w("Greeting2", "Erreur de lecture des données des chambres : ${error.message}")
//                    }
//                })
//            }
//
//
//            // Dynamic room information based on user input
//            roomsState.value.forEachIndexed { index, room ->
//                Column(modifier = Modifier.fillMaxWidth()) {
//                    Text(text = "Chambre ${index + 1}", modifier = Modifier.padding(top = 8.dp))
//
//                    // Placeholder for individual room price (not used)
//                    TextField(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 16.dp),
//                        value = room.price.value, // Placeholder, not used
//                        onValueChange = { /* Handle price change if needed */ },
//                        label = { Text(text = "Prix de la chambre") },
//                        enabled = false // Disable price editing
//                    )
//
//                    Spacer(modifier = Modifier.height(8.dp))
//
//                    // TextFields for number of beds and bathrooms (no initial values)
//                    TextField(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 16.dp),
//                        value = room.numberOfBed.value,
//                        onValueChange = { room.numberOfBed.value = it },
//                        label = { Text(text = "Nombre de lit dans la chambre") },
//                    )
//
//                    Spacer(modifier = Modifier.height(8.dp))
//                    TextField(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 16.dp),
//                        value = room.numberOfBathroom.value,
//                        onValueChange = { room.numberOfBathroom.value = it },
//                        label = { Text(text = "Nombre de salle de bain") },
//                    )
//
//                    Spacer(modifier = Modifier.height(8.dp))
//
//                    // Add button to pick images for each room (optional)
//                    /*
//                    Button(
//                        onClick = {
//                            launcher.launch(ActivityResultContracts.PickMultipleVisualMedia.Input(maxSize = 1)) // Limit to 1 image per room
//                        }
//                    ) {
//                        Text("Choisir des images")
//                    }
//                    */
//                }
//            }
//
//            // Button to submit or save the room information
//            Button(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp),
//                onClick = {
////                    // Handle form submission logic here
////                    // Validate all fields before saving
////                    if (number.value.isEmpty() || adresse.value.isEmpty() ) {
////                        Toast.makeText(context, "Veuillez remplir tous les champs obligatoires", Toast.LENGTH_SHORT).show()
////                    } else {
////                        // Save room information (implementation depends on your backend)
////                        Toast.makeText(context, "Informations enregistrées avec succès", Toast.LENGTH_SHORT).show()
////                        // Consider resetting the form after successful submission
////                    }
//                }
//            ) {
//                Text("Enregistrer")
//            }
//        }
//    }
//}

















//            (){
//    val number = remember { mutableStateOf("") }
//    val adresse = remember { mutableStateOf("") }
//    val apercuImages = remember { mutableStateListOf<Image>() }
//    val numberOfBathroom = remember { mutableStateOf("") }
//    val price = remember { mutableStateOf("") }
//    val numberOfBedroom = remember { mutableStateOf("") }
//    val numberOfBed = remember { mutableStateOf("") }
//
//
//    val context= LocalContext.current as Activity
//
//    val contextResolver = LocalContext.current
//    val image = remember { mutableStateOf<ImageBitmap?>(null) }
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.PickVisualMedia(),
//        onResult = {
//            val bitmap = try {
//                val inputStream = contextResolver.contentResolver.openInputStream(it!!)
//                BitmapFactory.decodeStream(inputStream)
//            } catch (e: Exception) {
//                Toast.makeText(contextResolver, "Erreur $e", Toast.LENGTH_SHORT).show()
//                null
//            }
//
//            image.value = bitmap?.asImageBitmap()
//        },
//    )
//
//
//
//    Scaffold(topBar = {
//        TopAppBar(title = { Text(text = "Ajouter les informations des chambres",
//            textAlign = TextAlign.Center) },
//            navigationIcon = {
//                IconButton(onClick = {context.finish()}) {
//                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription =null)
//                }
//            })
//    }) {
//        Column(modifier = Modifier.padding(paddingValues = it)) {
//
//            // Champ de saisie pour le nom
//            TextField(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp),
//                value = number.value,
//                onValueChange = { number.value = it },
//                label = { Text(text = "Numéro de la chambre") },
//
//                )
//
//            // ADRESSE
//            TextField(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp),
//                value = adresse.value,
//                onValueChange = { adresse.value = it },
//                label = { Text(text = "Adresse") },
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            // Champ de saisie pour le type
//            TextField(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp),
//                value = price.value,
//                onValueChange = { price.value = it },
//                label = { Text(text = "Prix de la chambre") },
//
//                )
//
//            Spacer(modifier = Modifier.height(8.dp))
//            // Champ de saisie pour la présentation
//            TextField(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp),
//                value = numberOfBed.value,
//                onValueChange = { numberOfBed.value = it },
//                label = { Text(text = "Nombre de lit dans la chambre") },
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//
//            TextField(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp),
//                value = numberOfBathroom.value,
//                onValueChange = { numberOfBathroom.value = it },
//                label = { Text(text = "Nombre de salle de bain") },
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//
//            TextField(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp),
//                value = numberOfBedroom.value,
//                onValueChange = { numberOfBedroom.value = it },
//                label = { Text(text = "Nombre de chambre") },
//            )
//
//
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            if (image.value != null) {
//                Image(
//                    bitmap = image.value!!, contentDescription = "",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .size(128.dp)
//                        .clickable {
//                            launcher.launch(PickVisualMediaRequest())
//                        },
//                )
//            } else {
//                Button(
//                    modifier = Modifier
//                        .size(128.dp)
//                        .clip(CircleShape),
//                    onClick = { launcher.launch(PickVisualMediaRequest()) }) {
//                }
//            }
//
//
//            // Bouton pour enregistrer
//            Button(
//                onClick = {
//                    // onSave(nom, type, presentation)
//                },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(text = "Enregistrer")
//            }
//        }
//    }
//}
//
