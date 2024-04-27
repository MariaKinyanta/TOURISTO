package org.unh.touristo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import org.unh.touristo.login.LoginViewModel
import org.unh.touristo.ui.theme.TouristoTheme



class MonCompteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val content = setContent {
            TouristoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    MonCompteScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonCompteScreen(
    //viewModel: AccountViewModel?= AccountViewModel(),
    ) {

   // val uiState = viewModel?.uiState

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Mon compte", textAlign = TextAlign.Center) })
        },

        ) {

        Column(modifier = Modifier.padding(paddingValues = it)) {
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(250.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.photo),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape = GenericShape { size, _ ->
                            addOval(Rect(0f, 0f, size.width, size.height))
                        })
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                        text = "MARIA KINYANTA",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                .padding(start = 16.dp)
                    )

                    Text(
                        text = "Touriste",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier
                            .padding(start = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Kinyantamaria@gmail.com",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier
                            .padding(start = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                }
        }
    }








//                if (uiState.value.isLoading) {
//                    // Show a loading indicator while fetching data
//                    CircularProgressIndicator()
//                } else if (uiState.value.errorMessage != null) {
//                    // Display error message if there's an error
//                    Text(text = uiState.value.errorMessage!!)
//                } else {
//                    // User data is loaded successfully, display the information
//                    val userInfo = uiState.value.userInfo!!
//
//                    // Display user image (if available)
//                    if (userInfo.imageUrl != null) {
//                        Image(
//                            modifier = Modifier
//                                .size(100.dp)
//                                .clip(CircleShape),
//                            contentDescription = "User profile picture",
//                            imageBitmap = remember {
//                                mutableStateOf(
//                                    ImageBitmap.imageFilePath(
//                                        userInfo.imageUrl!!
//                                    )
//                                )
//                            },
//                            loading = {
//                                CircularProgressIndicator()
//                            },
//                            error = {
//                                Image(
//                                    imageVector = ImageVector.system(name = "broken_image"),
//                                    contentDescription = "Error loading image"
//                                )
//                            }
//                        )
//                    }
//
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    Text(text = "Nom: ${userInfo.name ?: "Nom non renseigné"}")
//                    Text(text = "Prénom: ${userInfo.lastName ?: "Prénom non renseigné"}")
//                    Text(text = "Email: ${userInfo.email}")
//                    Text(text = "Adresse email: ${userInfo.address ?: "Adresse email non renseignée"}")
 //               }







