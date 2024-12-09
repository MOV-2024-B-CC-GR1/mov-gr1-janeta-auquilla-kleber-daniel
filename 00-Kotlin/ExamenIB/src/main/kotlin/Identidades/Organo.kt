package org.example.Identidades

// Clase Organo
data class Organo(
    val nombre: String,      // Nombre del órgano
    val peso: Double,        // Peso del órgano en gramos
    val color: String,       // Color del órgano
    val esComestible: Boolean, // Si es comestible o no
    val descripcion: String  // Breve descripción del órgano
)
