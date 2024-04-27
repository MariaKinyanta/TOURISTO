package org.unh.touristo.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.unh.touristo.domaine.Historique

@Database(entities = [(Historique::class)], version = 1, exportSchema = false)
abstract class HistoriqueRoomDatabase : RoomDatabase() {

    abstract fun historiqueDao(): HistoriqueDao

    companion object {

        @Volatile
        private var INSTANCE: HistoriqueRoomDatabase? = null

        fun getInstance(context: Context): HistoriqueRoomDatabase {
            // only one thread of execution at a time can enter this block of code
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HistoriqueRoomDatabase::class.java,
                        "Historique_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}