//package org.unh.touristo.repository
//
//import com.google.firebase.firestore.FirebaseFirestore
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import org.unh.touristo.domaine.Chambre
//import org.unh.touristo.domaine.Destination
//
//class ChambreRepository {
//    val firestore = FirebaseFirestore.getInstance()
//    val collection = "chambre"
//    suspend fun createChambre(chambre: Chambre, onSuccess: () -> Unit) =
//        withContext(Dispatchers.IO) {
//            firestore.collection(collection).add(chambre).addOnCompleteListener {
//                if (
//                    it.isComplete
//                ) {
//                    onSuccess()
//                }
//            }
//        }
//
//    suspend fun deleteChambre(id: String, onSuccess: () -> Unit) = withContext(Dispatchers.IO) {
//        firestore.collection(collection).document(id).delete().addOnCompleteListener {
//            if (
//                it.isComplete
//            ) {
//                onSuccess()
//            }
//        }
//    }
//
//    suspend fun updateChambre(id: String, chambre: Chambre, onSuccess: () -> Unit) =
//        withContext(Dispatchers.IO) {
//            val updated = mutableMapOf<String, Any>()
//            updated.put("number", chambre.number)
//            updated.put("adresse", chambre.adresse)
//            updated.put("price", chambre.price)
//            updated.put("id", chambre.id)
//            updated.put("idHebergement", chambre.idHebergement)
//            updated.put("numberOfBed", chambre.numberOfBed)
//            updated.put("numberOfBedroom", chambre.numberOfBedroom)
//            updated.put("numberOfBathroom", chambre.numberOfBathroom)
//            updated.put("disponible", chambre.disponible)
//            firestore.collection(collection).document(id).update(updated).addOnCompleteListener {
//                if (
//                    it.isComplete
//                ) {
//                    onSuccess()
//                }
//                    }
//                }
//
//
//
//    suspend fun getAllChambre(onSuccess: (List<Chambre>) -> Unit) =
//        withContext(Dispatchers.IO) {
//            firestore.collection(collection).get().addOnCompleteListener {
//                if (
//                    it.isComplete
//                ) {
//                    val chambres = mutableListOf<Chambre>()
//                    it.result.documents.map { chambre ->
//                        chambres.add(
//                            Chambre(
//                                number = chambre.get("number").toString(),
//                                price = chambre.get("price").toString(),
//                                adresse = chambre.get("adresse").toString(),
//                                id = chambre.get("id").toString(),
//                                idHebergement = chambre.get("idHebergement").toString(),
//                                numberOfBed = chambre.get("numberOfBed").toString(),
//                                numberOfBedroom = chambre.get(" numberOfBedroom").toString(),
//                                numberOfBathroom= chambre.get("numberOfBathroom").toString(),
//                                disponible= chambre.get("disponible").toString(),
//                            )
//                        )
//                    }
//                    onSuccess(chambres)
//                }
//            }
//        }
//}