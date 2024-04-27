package org.unh.touristo.viewModel

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.unh.touristo.domaine.Hebergement
import org.unh.touristo.repository.HebergementRepository
import org.unh.touristo.repository.StorageRepository

class HebergementViewModel(
    private val hebergementRepository: HebergementRepository = HebergementRepository(),
    private val storageRepository: StorageRepository = StorageRepository(),
) : ViewModel() {

    private var _hebergementUiState = mutableStateOf(HebergementUiState())
    private var _hebergementDetailsUiState = mutableStateOf(HebergementDetailsUiState())
    val hebergementUiState = _hebergementUiState
    val hebergementDetailsUiState = _hebergementDetailsUiState

    init {
        getAllHebergement()
    }

    fun addHebergement(uri: Uri, hebergement: Hebergement, onSuccess: () -> Unit) =
        viewModelScope.launch {
            storageRepository.addFiles(uri) {
                addHebergementWithImage(hebergement = hebergement, url = it) {
                    onSuccess()
                }
            }

        }

    private fun addHebergementWithImage(hebergement: Hebergement, url: String, onSuccess: () -> Unit) = viewModelScope.launch {
        val addedHebergement = Hebergement(
            name = hebergement.name,
            price = hebergement.price,
            url = url,
            adresse = hebergement.adresse,
            lienHebergement = hebergement.lienHebergement,
            id = hebergement.id,
            longetitude = hebergement.longetitude,
            latitude = hebergement.latitude,
            description = hebergement.description,

        )
        hebergementRepository.createHebergement(addedHebergement, onSuccess = { onSuccess() })
    }

    private fun getAllHebergement() = viewModelScope.launch {
        hebergementRepository.getAllHebergement {
            _hebergementUiState.value = HebergementUiState(it)
        }
    }
    fun deleteHebergement (id: String) = viewModelScope.launch {
        hebergementRepository.deleteHebergement(id) {
            getAllHebergement()
        }

    }
    fun getId(id: String)=viewModelScope.launch {
        hebergementRepository.getHebergementById(id, onSuccess = {
            _hebergementDetailsUiState.value=hebergementDetailsUiState.value.copy(it!!)
        }){


        }
    }

}

data class HebergementUiState(
    val hebergements: List<Hebergement> = emptyList()
)
data class HebergementDetailsUiState(
    val hebergement: Hebergement? =null,
)