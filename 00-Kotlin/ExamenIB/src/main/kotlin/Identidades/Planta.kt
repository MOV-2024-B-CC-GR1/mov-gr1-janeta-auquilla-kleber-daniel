package org.example.Identidades

// Clase Planta
data class Planta(
    var nombre: String,              // Nombre de la planta
    var altura: Int,                 // Altura de la planta en cm
    var tipo: String,                // Tipo de planta (Medicinal, Ornamental, etc.)
    var esPerenne: Boolean,          // Si es perenne o no
    val organos: MutableList<Organo> // Lista de órganos asociados
)
