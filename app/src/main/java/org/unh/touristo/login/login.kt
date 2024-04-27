package org.unh.touristo.login

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.unh.touristo.FormActivity
import org.unh.touristo.R
import org.unh.touristo.choixVilleActivity

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel?= LoginViewModel(),
    onNavTochoixVilleActivity: ()->Unit,
    onNavToFormActivity: () -> Unit,
) {
    val loginUiState=loginViewModel?.LoginUiState
    val isError=loginUiState?.loginError!=null
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val primaryColor = androidx.compose.ui.graphics.Color(0xFF009688)
    val secondaryColor = androidx.compose.ui.graphics.Color(0xFFF9F9F9)

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top // Align content at the top
    ) {
        Box( // Stack the image and text on top of each other
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(250.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.cathedrale),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp))
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = Color.Black.copy(alpha = 0.1F))

            ) {

            }
            Text(
                text = "Touristo",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.TopCenter) // Center the text within the box
            )
            if (isError){
                Text(text = loginUiState?.loginError?:"Erreur inconnue",color= Color.Red,)
            }
        }


        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            value = email.value,
            //value= loginUiState?.userName?:"",
            //onValueChange = {loginViewModel?.onUserNameChange(it)},
            leadingIcon = {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "")
            },
            onValueChange = { email.value = it },
            label = { Text("Email") },isError=isError,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .background(secondaryColor)
                .padding(10.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
             value = password.value,
            //value = loginUiState?.password?:"",
            onValueChange = { password.value = it },
           /// onValueChange = {loginViewModel?.onPasswordrNameChange(it)},
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "")
            },
            label = { Text("Mot de passe") },
            visualTransformation = PasswordVisualTransformation(),
            isError=isError,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .background(secondaryColor)
                .padding(10.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            //onClick = { val intent = Intent(context, choixVilleActivity::class.java);
            // context.startActivity(intent) },
            onClick = {loginViewModel?.loginUser(context, email=email.value, password =password.value)
            {
                val intent = Intent(context, choixVilleActivity::class.java);
                context.startActivity(intent)

            }},

            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp),
            contentPadding = PaddingValues(vertical = 12.dp),
        ) {
            Text(text = "Se connecter", color = Color.White)
        }
        Spacer(modifier = Modifier.height(70.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 17.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Mot de passe oublié ?",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable { /* TODO: Handle Forgot Password Click */ },
                textDecoration = TextDecoration.Underline,
                color = Color.Blue,
                fontSize = 10.sp
            )
            Text(
                text = "Vous n'avez pas de compte ?",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable {
                        val intent = Intent(context, FormActivity::class.java);
                        context.startActivity(intent)
                    },
                textDecoration = TextDecoration.Underline,
                color = Color.Blue,
                fontSize = 10.sp
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
    if (loginUiState?.isLoading==true){
        CircularProgressIndicator()
    }
    LaunchedEffect(key1 =loginViewModel?.hasUser ) {
        if (loginViewModel?.hasUser==true){
            onNavTochoixVilleActivity.invoke()
        }
    }


}