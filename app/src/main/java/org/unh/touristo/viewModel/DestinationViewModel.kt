package org.unh.touristo.viewModel

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.unh.touristo.domaine.Destination
import org.unh.touristo.domaine.Historique
import org.unh.touristo.repository.DestinationRepository
import org.unh.touristo.repository.StorageRepository

class DestinationViewModel(
    private val destinationRepository: DestinationRepository = DestinationRepository(),
    private val storageRepository: StorageRepository = StorageRepository(),
) : ViewModel() {

    private var _destinationsUiState = mutableStateOf(DestinationUiState())
    private var _destinationDetailsUiState = mutableStateOf(DestinationDetailsUiState())
    val destinationUiState = _destinationsUiState
    val destinationDetailsUiState = _destinationDetailsUiState

    init {
        getAllDestination()
    }

    fun addDestination(uri: Uri, destination: Destination, onSuccess: () -> Unit) =
        viewModelScope.launch {
            storageRepository.addFiles(uri) {
                addDestinationWithImage(destination = destination, url = it) {
                    onSuccess()
                }
            }

        }

    private fun addDestinationWithImage(destination: Destination, url: String, onSuccess: () -> Unit) = viewModelScope.launch {
        val addedDestination = Destination(
            name = destination.name,
            type = destination.type,
            url = url,
            adresse = destination.adresse,
            id = destination.id,
            presentation = destination.presentation,
            activites = destination.activites,
            horairesOuverture = destination.horairesOuverture,
            horairesFermeture = destination.horairesFermeture,
            prixEntree = destination.prixEntree,
            consignes = destination.consignes,
            latitude = destination.latitude,
            longitude = destination.longitude
        )
        destinationRepository.createDestination(addedDestination, onSuccess = { onSuccess() })
    }

    private fun getAllDestination() = viewModelScope.launch {
        destinationRepository.getAllDestination {
            _destinationsUiState.value = DestinationUiState(destinations = it)
        }
    }

    fun deleteDestination (id: String) = viewModelScope.launch {
        destinationRepository.deleteDestination(id) {
            getAllDestination()
        }

    }
    fun getId(id: String)=viewModelScope.launch {
        destinationRepository.getDestinationById(id, onSuccess = {
            _destinationDetailsUiState.value=destinationDetailsUiState.value.copy(it!!)
        }){


        }
    }
    fun addHistorique( historique: Historique) {

    }
}

data class DestinationUiState(
    val destinations: List<Destination> = emptyList()
)

data class DestinationDetailsUiState(
    val destination: Destination?=null
)
