package org.unh.touristo

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import org.unh.touristo.login.LoginUiState
import org.unh.touristo.login.LoginViewModel
import org.unh.touristo.ui.theme.TouristoTheme

class FormActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TouristoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FormScreen()
                }
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(
    loginViewModel: LoginViewModel?= LoginViewModel(),

    ) {//ajout
    val loginUiState=loginViewModel?.LoginUiState
    val isError= loginUiState?.signUpError!=null


// type de compte
    val options = listOf(
        "Touriste",
        "Administrateur",

    )
    val nom = remember { mutableStateOf("") }
    val prenom = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    var typeDeCompte = remember { mutableStateOf(options[0]) }
    var textFiledSize by remember {
        mutableStateOf(Size.Zero)
    }

    val primaryColor = Color(0xFF0000FF)
    val secondaryColor = Color(0xFF00FF00)
    var expanded by remember {
        mutableStateOf(false)
    }

    val contextResolver = LocalContext.current

    val image = remember { mutableStateOf<ImageBitmap?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            val bitmap = try {
                val inputStream = contextResolver.contentResolver.openInputStream(it!!)
                BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
                Toast.makeText(contextResolver, "Erreur $e", Toast.LENGTH_SHORT).show()
                null
            }

            image.value = bitmap?.asImageBitmap()
        },
    )


    val context = (LocalContext.current as Activity)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Page d'inscription")
                    //ajout
                    if (isError){
                        Text(text = loginUiState?.signUpError?:"Erreur inconnue",color= Color.Red,)
                    }

                        },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primary
                //),
                navigationIcon = {
                    IconButton(onClick = { context.finish() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                },
            )
        },
    ) { it ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            if (image.value != null) {
                Image(
                    bitmap = image.value!!, contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(128.dp)
                        .clip(CircleShape)
                        .clickable {
                            launcher.launch(PickVisualMediaRequest())
                        },
                )
            } else {
                Button(
                    modifier = Modifier
                        .size(128.dp)
                        .clip(CircleShape),
                    onClick = { launcher.launch(PickVisualMediaRequest()) }) {
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Choisir une photo de profil")

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
               value = nom.value,
               onValueChange = { nom.value = it },



                label = { Text("Nom") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = prenom.value,
                onValueChange = { prenom.value = it },
                label = { Text("Prénom") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email") }
            )

            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = password.value,
                //value = loginUiState?.password?:"",
                onValueChange = { password.value = it },
                /// onValueChange = {loginViewModel?.onPasswordrNameChange(it)},
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "")
                },
                label = { Text("Mot de passe") },

                isError=isError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        readOnly = true,
                        value = typeDeCompte.value, onValueChange = {},
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }) {
                        options.forEachIndexed { index, s ->
                            DropdownMenuItem(
                                text = { Text(s) },
                                onClick = {
                                    typeDeCompte.value = options[index]
                                    expanded = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                            )
                        }
                    }
                }

            }




            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {

                              loginViewModel?.createUser(context,email.value, password=password.value)

                              {
                                  val intent = Intent(context, choixVilleActivity::class.java);
                                  context.startActivity(intent)
                              }
                    },
                    modifier = Modifier.fillMaxWidth(0.5f),
                ) {
                    Text("S'enregistrer")
                }

                Spacer(modifier = Modifier.width(16.dp))


            }

        }
    }
}

