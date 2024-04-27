package org.unh.touristo.repository

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.unh.touristo.domaine.Hebergement
import org.unh.touristo.domaine.Restaurant


class RestaurantRepository{
    val firestore = FirebaseFirestore.getInstance()
    val collection = "restaurant"
    suspend fun createRestaurant(restaurant: Restaurant, onSuccess: () -> Unit) =
        withContext(Dispatchers.IO) {
            firestore.collection(collection).add(restaurant).addOnCompleteListener {
                if (
                    it.isComplete
                ) {
                    onSuccess()
                }
            }
        }

    suspend fun deleteRestaurant(id: String, onSuccess: () -> Unit) = withContext(Dispatchers.IO) {
        firestore.collection(collection).document(id).delete().addOnCompleteListener {
            if (
                it.isComplete
            ) {
                onSuccess()
            }
        }
    }

    suspend fun updateRestaurant(id: String, restaurant: Restaurant, onSuccess: () -> Unit) =
        withContext(Dispatchers.IO) {
            val updated = mutableMapOf<String, Any>()
            updated.put("name", restaurant.name)
            updated.put("adresse", restaurant.adresse)
            updated.put("id", restaurant.id)
            updated.put("lienRestaurant", restaurant.lienRestaurant)
            updated.put("description", restaurant.description)
            updated.put("longetitude", restaurant.longetitude)
            updated.put("latitude", restaurant.latitude)
            //updated.put("url", restaurant.url)
           // updated.put("details", restaurant.details)
            firestore.collection(collection).document(id).update(updated).addOnCompleteListener {
                if (
                    it.isComplete
                ) {
                    onSuccess()
                }
            }
        }

    suspend fun getAllRestaurant(onSuccess: (List<Restaurant>) -> Unit) =
        withContext(Dispatchers.IO) {
            firestore.collection(collection).get().addOnCompleteListener {
                if (
                    it.isComplete
                ) {
                    val restaurants = mutableListOf<Restaurant>()
                    it.result.documents.map { restaurant ->
                        restaurants.add(
                            Restaurant(
                                name = restaurant.get("name").toString(),
                                adresse = restaurant.get("adresse").toString(),
                                id = restaurant.get("id").toString(),
                                description = restaurant.get("description").toString(),
                                lienRestaurant = restaurant.get("lienRestaurant").toString(),
                                url = restaurant.get("url").toString(),
                                longetitude = restaurant.get("longetitude").toString(),
                                latitude = restaurant.get("latitude").toString()
                                //details = restaurant.get("details").toString(),
                            )
                        )
                    }
                    onSuccess(restaurants)
                }
            }
        }
    suspend fun getRestaurantById(
        id: String,
        onSuccess: (Restaurant?) -> Unit,
        onError: (Throwable) -> Unit = {}
    ) {
        var restaurant by mutableStateOf<Restaurant?>(null)

        withContext(Dispatchers.IO) {
            val docRef = firestore.collection(collection).document(id)
            docRef.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val data = documentSnapshot.data ?: return@addOnSuccessListener
                    restaurant=Restaurant(
                        name = data.get("name").toString(),
                        description = data.get("desciption").toString(),
                        adresse = data.get("adresse").toString(),
                        id = documentSnapshot.id,
                        lienRestaurant = data.get("lienRestaurant").toString(),
                        url = data.get("url").toString(),
                        longetitude = data.get("longetitude").toString(),
                        latitude = data.get("latitude").toString(),
                        //details = data.get("details").toString(),



                        )
                    onSuccess(restaurant)
                } else {
                    onSuccess(null) // Indicate no destination found with the ID
                }
            }.addOnFailureListener { exception ->
                onError(exception)
            }
        }
    }
    suspend fun getRestauranttById(
        id: String,
        onSuccess: (Restaurant?) -> Unit,
        onError: (Throwable) -> Unit = {}
    ) {
        var restaurant by mutableStateOf<Restaurant?>(null)

        withContext(Dispatchers.IO) {
            val docRef = firestore.collection(collection).document(id)
            docRef.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val data = documentSnapshot.data ?: return@addOnSuccessListener
                    restaurant=Restaurant(
                        name = data.get("name").toString(),
                        description = data.get("description").toString(),
                        adresse = data.get("adresse").toString(),
                        id = documentSnapshot.id,
                        url = data.get("url").toString(),
                        lienRestaurant = data.get("lienRestaurant").toString(),
                        longetitude= data.get("longetitude").toString(),
                        latitude = data.get("latitude").toString(),

                        )
                    onSuccess(restaurant)
                } else {
                    onSuccess(null) // Indicate no destination found with the ID
                }
            }.addOnFailureListener { exception ->
                onError(exception)
            }
        }
    }
}