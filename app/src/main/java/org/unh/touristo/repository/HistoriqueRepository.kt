package org.unh.touristo.repository

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.unh.touristo.domaine.Historique

@Dao
interface HistoriqueDao {
    @Upsert
    suspend fun insertHistorique(entry: Historique)

    @Query("SELECT * FROM history ")
    suspend fun getHistoryForUser(): List<Historique>

    @Delete
    suspend fun deleteHistoriqueDetails(entry: Historique)
}


class HistoriqueRepository(private val historique: HistoriqueDao) {

    val allHistorique = mutableListOf<Historique>()
    val foundHistorique = MutableLiveData<Historique>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun InsertHistorique(newHistorique: Historique) {
        coroutineScope.launch(Dispatchers.IO) {
            historique.insertHistorique(newHistorique)
        }
    }

     fun getHistorique(){

     }


    }