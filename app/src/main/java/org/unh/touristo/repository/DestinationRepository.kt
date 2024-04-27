package org.unh.touristo.repository

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.unh.touristo.domaine.Destination
import java.util.Objects


class DestinationRepository {
    val firestore = FirebaseFirestore.getInstance()
    val collection = "destination"
    suspend fun createDestination(destination: Destination, onSuccess: () -> Unit) {
        withContext(Dispatchers.IO) {
            firestore.collection(collection).add(destination).addOnCompleteListener {
                if (
                    it.isComplete
                ) {
                    onSuccess()
                }
            }
        }}

    suspend fun deleteDestination(id: String, onSuccess: () -> Unit) = withContext(Dispatchers.IO) {
        firestore.collection(collection).document(id).delete().addOnCompleteListener {
            if (
                it.isComplete
            ) {
                onSuccess()
            }
        }
    }

    suspend fun updateDestination(id: String, destination: Destination, onSuccess: () -> Unit) =
        withContext(Dispatchers.IO) {
            val updated = mutableMapOf<String, Any>()
            updated.put("name", destination.name)
            updated.put("adresse", destination.adresse)
            updated.put("type", destination.type)
            updated.put("id", destination.id)
            updated.put("presentation", destination.presentation)
            updated.put("activites", destination.activites)
            updated.put("horairesOuverture", destination.horairesOuverture)
            updated.put("horairesFermeture", destination.horairesFermeture)
            updated.put("prixEntree", destination.prixEntree)
            updated.put("consignes", destination.consignes)
            updated.put("url", destination.url)
            updated.put("latitude", destination.latitude)
            updated.put("longitude", destination.longitude)
            firestore.collection(collection).document(id).update(updated).addOnCompleteListener {
                if (
                    it.isComplete
                ) {
                    onSuccess()
                }
            }
        }

    suspend fun getAllDestination(onSuccess: (List<Destination>) -> Unit) =
        withContext(Dispatchers.IO) {
            firestore.collection(collection).get().addOnCompleteListener {
                if (
                    it.isComplete
                ) {
                    val destinations = mutableListOf<Destination>()
                    it.result.documents.map { destination ->
                        destinations.add(
                            Destination(
                                name = destination.get("name").toString(),
                                type = destination.get("type").toString(),
                                adresse = destination.get("adresse").toString(),
                                id = destination.id,
                                consignes = destination.get("consignes").toString(),
                                presentation = destination.get("presentation").toString(),
                                activites = destination.get("activites").toString(),
                                horairesOuverture = destination.get("horairesOuverture").toString(),
                                horairesFermeture = destination.get("horairesFermeture").toString(),
                                latitude = destination.get("latitude").toString(),
                                longitude = destination.get("longitude").toString(),
                                prixEntree = destination.get("prixEntree").toString(),
                                url = destination.get("url").toString(),

                            )
                        )
                    }
                    onSuccess(destinations)
                }
            }
        }



    suspend fun getDestinationById(
        id: String,
        onSuccess: (Destination?) -> Unit,
        onError: (Throwable) -> Unit = {}
    ) {
        var destination by mutableStateOf<Destination?>(null)

        withContext(Dispatchers.IO) {
            val docRef = firestore.collection(collection).document(id)
            docRef.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val data = documentSnapshot.data ?: return@addOnSuccessListener
                    destination = Destination(
                        name = data.get("name").toString(),
                        type = data.get("type").toString(),
                        adresse = data.get("adresse").toString(),
                        id = documentSnapshot.id,
                        consignes = data.get("consignes").toString(),
                        presentation = data.get("presentation").toString(),
                        activites = data.get("activites").toString(),
                        horairesOuverture = data.get("horairesOuverture").toString(),
                        horairesFermeture = data.get("horairesFermeture").toString(),
                        longitude = data.get("longitude").toString(),
                        latitude = data.get("latitude").toString(),
                        prixEntree = data.get("prixEntree").toString(),
                        url = data.get("url").toString(),

                    )
                    onSuccess(destination)
                } else {
                    onSuccess(null) // Indicate no destination found with the ID
                }
            }.addOnFailureListener { exception ->
                onError(exception)
            }
        }
    }

}

