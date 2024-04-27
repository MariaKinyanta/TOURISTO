package org.unh.touristo.login

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Toast
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.unh.touristo.repository.AuthRepository
import java.lang.Exception





class LoginViewModel(
    private val repository: AuthRepository= AuthRepository()
):ViewModel() {
    val currentUser = repository.currentUser
    val hasUser: Boolean
        get() = repository.hasUser()
    var LoginUiState by mutableStateOf(LoginUiState())
        private set

    fun onUserNameChange(userName: String) {
        LoginUiState = LoginUiState.copy(userName = userName)
    }

    fun onPasswordrNameChange(password: String) {
        LoginUiState = LoginUiState.copy(password = password)
    }

    fun onUserNameChangeSignup(userName: String) {
        LoginUiState = LoginUiState.copy(userNameSignUp = userName)
    }

    fun onPasswordChangeSingUp(password: String) {
        LoginUiState = LoginUiState.copy(passwordSignUp = password)
    }

    fun onConfirmPasswordChangeSingUp(password: String) {
        LoginUiState = LoginUiState.copy(confirmpasswordSignUp = password)
    }

    private fun validateLoginForm() =
        LoginUiState.userName.isNotBlank() &&
                LoginUiState.password.isNotBlank()

    private fun validateSignupForm() =
        LoginUiState.userNameSignUp.isNotBlank() &&
                LoginUiState.passwordSignUp.isNotBlank()


    fun createUser(context: Context,email:String,password: String, onSuccess:()->Unit) = viewModelScope.launch {
        try {
//            if (!validateSignupForm()) {
//                throw IllegalArgumentException("L'email et le mot de passe n'existent pas")
//            }
            LoginUiState=LoginUiState.copy(isLoading = true )
            if (LoginUiState.passwordSignUp!=
                LoginUiState.confirmpasswordSignUp){
                throw IllegalArgumentException(
                    "Les mots de passe ne correspodent pas"
                )
            }
            LoginUiState=LoginUiState.copy(signUpError = null)
            repository.createUser(
                email = email,
                password = password,
            ){isSuccessful ->
                if(isSuccessful){
                    Toast.makeText(
                        context,
                        "Enregistrement réussi",
                        Toast.LENGTH_SHORT
                    ).show()
                    LoginUiState=LoginUiState.copy(isSuccesLogin = true)

                    onSuccess()
                }else{
                    Toast.makeText(
                        context,
                        "Enregistrement échoué",
                        Toast.LENGTH_SHORT
                    ).show()
                    LoginUiState=LoginUiState.copy(isSuccesLogin = false)
                }
            }

        } catch (e:Exception){
            LoginUiState=LoginUiState.copy(signUpError =e.localizedMessage )
            e.printStackTrace()
    }finally {
       LoginUiState=LoginUiState.copy(isLoading = false)
    }
}


    fun loginUser(context: Context,email:String,password: String, onSuccess:()->Unit) = viewModelScope.launch {
        try {
//            if (!validateLoginForm()) {
//                throw IllegalArgumentException("L'email et le mot de passe n'existent pas")
//            }
            LoginUiState=LoginUiState.copy(isLoading = true )
            LoginUiState=LoginUiState.copy(loginError = null)
            repository.login(
                email = email,
                password=password
            ){isSuccessful ->
                if(isSuccessful){
                    Toast.makeText(
                        context,
                        "Enregistrement réussi",
                        Toast.LENGTH_SHORT
                    ).show()
                    LoginUiState=LoginUiState.copy(isSuccesLogin = true)
                    onSuccess()
                }else{
                    Toast.makeText(
                        context,
                        "Enregistrement échoué",
                        Toast.LENGTH_SHORT
                    ).show()
                    LoginUiState=LoginUiState.copy(isSuccesLogin = false)
                }
            }

        } catch (e:Exception){
            LoginUiState=LoginUiState.copy(loginError =e.localizedMessage )
            e.printStackTrace()
        }finally {
            LoginUiState=LoginUiState.copy(isLoading = false)
        }
    }
}


data class LoginUiState(
    val userName:String="",
    val password:String="",
    val userNameSignUp:String="",
    val passwordSignUp:String="",
    val confirmpasswordSignUp:String="",
    val isLoading:Boolean=false,
    val isSuccesLogin:Boolean=false,
    val signUpError:String?=null,
    val loginError:String?= null,
)