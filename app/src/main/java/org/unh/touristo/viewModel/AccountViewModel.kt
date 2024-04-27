//package org.unh.touristo.viewModel
//
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.google.firebase.firestore.FirebaseFirestore
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.tasks.await
//
//class AccountViewModel(
//    private val firestore: FirebaseFirestore,
//    private val currentUserId: String
//) : ViewModel() {
//
//    private val _uiState = mutableStateOf(UserInfoUiState())
//    val uiState: State<UserInfoUiState> = _uiState
//
//    fun getUserInfo() = viewModelScope.launch {
//        _uiState.value = uiState.value.copy(isLoading = true)
//        try {
//            val docRef = firestore.collection("users").document(currentUserId)
//            val document = docRef.get().await()
//            if (document.exists()) {
//                val userInfo = document.toObject(UserData::class.java) ?: return@launch
//                _uiState.value = uiState.value.copy(
//                    userInfo = userInfo,
//                    isLoading = false
//                )
//            } else {
//                _uiState.value = uiState.value.copy(
//                    errorMessage = "User data not found",
//                    isLoading = false
//                )
//            }
//        } catch (e: Exception) {
//            _uiState.value = uiState.value.copy(
//                errorMessage = e.localizedMessage,
//                isLoading = false
//            )
//            e.printStackTrace()
//        }
//    }
//}
//
//data class UserInfoUiState(
//    val userInfo: UserData? = null,
//    val isLoading: Boolean = false,
//    val errorMessage: String? = null
//)
//
//data class UserData(
//    val nom: String? = null,
//    val prenom: String? = null,
//    val password: String? = null,
//    val email: String? = null,
//
//)
