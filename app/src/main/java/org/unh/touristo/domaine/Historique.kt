package org.unh.touristo.domaine

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class Historique(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "user_id") val userId: String,
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "place_id") val placeId: String,
    @ColumnInfo(name = "place_name") val placeName: String,
    @ColumnInfo(name = "photo_url") val photoUrl: String?
)

