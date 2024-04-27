package org.unh.touristo.repository

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID



class StorageRepository {
    val firestore = FirebaseStorage.getInstance().reference


    suspend fun addFiles(uri: Uri, onSuccess: (String) -> Unit) {
        withContext(Dispatchers.IO) {
            val fileName = UUID.randomUUID().toString()  // Generate unique filename
            val imageRef = firestore.child("images/$fileName") // Create reference with filename
            imageRef.putFile(uri).addOnCompleteListener {
                imageRef.downloadUrl.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val downloadUrl = task.result.toString()
                        onSuccess(downloadUrl)
                    } else {

                    }
                }

            }
        }
    }
}
