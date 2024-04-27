package org.unh.touristo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.unh.touristo.ui.theme.TouristoTheme

class choixVilleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TouristoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ChoixvilleScreen ()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChoixvilleScreen() {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TOURISTO") },

                )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(paddingValues = it)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(8.dp)) // Add spacing between bar and text
                Text(
                    text = "Choisir une ville",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.align(Alignment.CenterHorizontally),

                )
                Spacer(modifier = Modifier.height(10.dp))
                // Button 1 (Lubumbashi)
                Button(
                    onClick = {
                        val intent = Intent(context, UsersActivity::class.java)
                        context.startActivity(intent)
                    },
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxWidth()
                        .padding(vertical = 8.dp), // Add some vertical padding
                    contentPadding = PaddingValues(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(

                    )
                ) {
                    Column()
                    {
                        Arrangement.Center.also { var horizontalArrangement = it }
                        Image(

                            painter = painterResource(id = R.drawable.lubumbqshi),
                            contentDescription = "city",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .size(width = 80.dp, height = 80.dp) // Set a fixed image size
                                .clip(RoundedCornerShape(12.dp))
                               .border( // Add a border for better separation
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.outline),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Lubumbashi",
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
                Spacer(modifier = Modifier.height(2.dp)) // Add spacing between buttons

                // Button 2 (Kolwezi)
                Button(
                    onClick = { val intent = Intent(context, DestinationKolweziActivity::class.java)
                        context.startActivity(intent) },
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxWidth()
                        .padding(vertical = 8.dp), // Add some vertical padding
                    contentPadding = PaddingValues(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(

                    )
                )
                {
                    Column()
                    {
                        Arrangement.Center.also { var horizontalArrangement = it }
                        Image(
                            painter = painterResource(id = R.drawable.kolwezi), // Replace with your image resource ID
                            contentDescription = "city",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .size(width = 80.dp, height = 80.dp) // Set a fixed image size
                                .clip(RoundedCornerShape(12.dp))
                                .border( // Add a border for better separation
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.outline),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = "Kolwezi",
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            style = MaterialTheme.typography.bodyLarge
                            )
                    }
                }
                Spacer(modifier = Modifier.height(2.dp)) // Add spacing between buttons

                // Button 3 (Likasi)
                Button(
                    onClick = {  val intent = Intent(context, DestinationKolweziActivity::class.java)
                        context.startActivity(intent) },
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxWidth()
                        .padding(vertical = 8.dp), // Add some vertical padding
                    contentPadding = PaddingValues(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(

                    )
                )
                {
                    Column()
                    {
                        Arrangement.Center.also { var horizontalArrangement = it }
                        Image(
                            painter = painterResource(id = R.drawable.likasi), // Replace with your image resource ID
                            contentDescription = "city",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .size(width = 80.dp, height = 80.dp) // Set a fixed image size
                                .clip(RoundedCornerShape(12.dp))
                                .border( // Add a border for better separation
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.outline),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = "Likasi",
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    )
}




