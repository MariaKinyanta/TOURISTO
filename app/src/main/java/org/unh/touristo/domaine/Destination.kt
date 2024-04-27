package org.unh.touristo.domaine

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

data class Destination(
    val name:String,
    val type:String,
    val adresse:String,
    val id:String,
    val presentation:String,
    val activites:String,
    val horairesOuverture:String,
    val horairesFermeture : String,
    val prixEntree : String,
    val consignes:String,
    val url:String,
    val latitude:String,
    val longitude:String,


)
