package org.unh.touristo.repository

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.unh.touristo.domaine.Destination
import org.unh.touristo.domaine.Hebergement

class HebergementRepository{
        val firestore = FirebaseFirestore.getInstance()
        val collection = "hebergement"
        suspend fun createHebergement(hebergement: Hebergement, onSuccess: () -> Unit) =
            withContext(Dispatchers.IO) {
                firestore.collection(collection).add(hebergement).addOnCompleteListener {
                    if (
                        it.isComplete
                    ) {
                        onSuccess()
                    }
                }
            }

        suspend fun deleteHebergement(id: String, onSuccess: () -> Unit) = withContext(Dispatchers.IO) {
            firestore.collection(collection).document(id).delete().addOnCompleteListener {
                if (
                    it.isComplete
                ) {
                    onSuccess()
                }
            }
        }

        suspend fun updateHebergement(id: String, hebergement: Hebergement, onSuccess: () -> Unit) =
            withContext(Dispatchers.IO) {
                val updated = mutableMapOf<String, Any>()
                updated.put("name", hebergement.name)
                updated.put("adresse", hebergement.adresse)
                updated.put("price", hebergement.price)
                updated.put("id", hebergement.id)
                updated.put("lienHebergement", hebergement.lienHebergement)
                updated.put("longetitude", hebergement.longetitude)

                firestore.collection(collection).document(id).update(updated).addOnCompleteListener {
                    if (
                        it.isComplete
                    ) {
                        onSuccess()
                    }
                }
            }

        suspend fun getAllHebergement(onSuccess: (List<Hebergement>) -> Unit) =
            withContext(Dispatchers.IO) {
                firestore.collection(collection).get().addOnCompleteListener {
                    if (
                        it.isComplete
                    ) {
                        val hebergements = mutableListOf<Hebergement>()
                        it.result.documents.map { hebergement ->
                            hebergements.add(
                                Hebergement(
                                    name = hebergement.get("name").toString(),
                                    price = hebergement.get("price").toString(),
                                    adresse = hebergement.get("adresse").toString(),
                                    id = hebergement.id,
                                    url = hebergement.get("url").toString(),
                                    longetitude = hebergement.get("longetitude").toString(),
                                    latitude = hebergement.get("latitude").toString(),
                                    lienHebergement = hebergement.get("lienHebergement").toString(),
                                    description = hebergement.get("description").toString(),
                                )
                            )
                        }
                        onSuccess(hebergements)
                    }
                }
            }
    suspend fun getHebergementById(
        id: String,
        onSuccess: (Hebergement?) -> Unit,
        onError: (Throwable) -> Unit = {}
    ) {
        var hebergement by mutableStateOf<Hebergement?>(null)

        withContext(Dispatchers.IO) {
            val docRef = firestore.collection(collection).document(id)
            docRef.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val data = documentSnapshot.data ?: return@addOnSuccessListener
                    hebergement=Hebergement(
                        name = data.get("name").toString(),
                        price = data.get("price").toString(),
                        adresse = data.get("adresse").toString(),
                        id = documentSnapshot.id,
                        url = data.get("url").toString(),
                        lienHebergement = data.get("lienHebergement").toString(),
                        description = data.get("description").toString(),
                        latitude = data.get("latitude").toString(),
                        longetitude = data.get("longetitude").toString(),

                        )
                    onSuccess(hebergement)
                } else {
                    onSuccess(null) // Indicate no destination found with the ID
                }
            }.addOnFailureListener { exception ->
                onError(exception)
            }
        }
    }
}