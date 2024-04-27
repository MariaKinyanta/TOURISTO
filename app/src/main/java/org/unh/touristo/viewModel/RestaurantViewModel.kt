package org.unh.touristo.viewModel

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.unh.touristo.domaine.Restaurant
import org.unh.touristo.repository.RestaurantRepository
import org.unh.touristo.repository.StorageRepository


class RestaurantViewModel(
    private val restaurantRepository: RestaurantRepository = RestaurantRepository(),
    private val storageRepository: StorageRepository = StorageRepository(),
) : ViewModel() {

    private var _restaurantUiState = mutableStateOf(RestaurantUiState())
    private var _restaurantDetailsUiState = mutableStateOf(RestaurantDetailsUiState())
    val restaurantUiState = _restaurantUiState
    val restaurantDetailsUiState = _restaurantDetailsUiState

    init {
        getAllRestaurant()
    }

    fun addRestaurant(uri: Uri, restaurant: Restaurant, onSuccess: () -> Unit) =
        viewModelScope.launch {
            storageRepository.addFiles(uri) {
                addRestaurantWithImage(restaurant=restaurant, url = it) {
                    onSuccess()
                }
            }

        }

    private fun addRestaurantWithImage(restaurant: Restaurant, url: String, onSuccess: () -> Unit) = viewModelScope.launch {
        val addedRestaurant = Restaurant(
            name = restaurant.name,
            description = restaurant.description,
            url = url,
            adresse = restaurant.adresse,
            id = restaurant.id,
            lienRestaurant = restaurant.lienRestaurant,
            latitude = restaurant.latitude,
            longetitude = restaurant.longetitude,
            //details = restaurant.details,



        )
        restaurantRepository.createRestaurant(addedRestaurant, onSuccess = { onSuccess() })
    }

    private fun getAllRestaurant() = viewModelScope.launch {
        restaurantRepository.getAllRestaurant {
            _restaurantUiState.value = RestaurantUiState(it)
        }
    }
    fun deleteRestaurant (id: String) = viewModelScope.launch {
        restaurantRepository.deleteRestaurant(id) {
            getAllRestaurant()
        }

    }
    fun getId(id: String)=viewModelScope.launch {
        restaurantRepository.getRestaurantById(id, onSuccess = {
            _restaurantDetailsUiState.value=restaurantDetailsUiState.value.copy(it!!)
        }){


        }
    }

}

data class RestaurantUiState(
    val restaurants: List<Restaurant> = emptyList()
)
data class RestaurantDetailsUiState(
    val restaurant: Restaurant?=null
)