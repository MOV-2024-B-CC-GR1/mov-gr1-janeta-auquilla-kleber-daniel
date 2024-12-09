package org.example.CRUD

import org.example.Identidades.Organo
import org.example.Identidades.Planta
import java.io.File

class Crud {
    private val plantasFile = File("resource/plantas.txt") // Archivo donde se guardarán las plantas
    private val plantas: MutableList<Planta> = mutableListOf()

    init {
        val directory = File("resource")
        if (!directory.exists()) directory.mkdir()
        loadFromFile()
    }

    private fun loadFromFile() {
        if (plantasFile.exists()) {
            plantasFile.forEachLine { line ->
                val parts = line.split("|")
                if (parts.size >= 4) {
                    val organos = parts.drop(4).chunked(5).map {
                        Organo(it[0], it[1].toDouble(), it[2], it[3].toBoolean(), it[4])
                    }
                    plantas.add(
                        Planta(
                            parts[0],
                            parts[1].toInt(),
                            parts[2],
                            parts[3].toBoolean(),
                            organos.toMutableList()
                        )
                    )
                }
            }
        }
    }

    private fun saveToFile() {
        plantasFile.bufferedWriter().use { writer ->
            plantas.forEach { planta ->
                val organosString = planta.organos.joinToString("|") {
                    "${it.nombre}|${it.peso}|${it.color}|${it.esComestible}|${it.descripcion}"
                }
                writer.write("${planta.nombre}|${planta.altura}|${planta.tipo}|${planta.esPerenne}|$organosString\n")
            }
        }
    }

    fun addPlanta(planta: Planta) {
        plantas.add(planta)
        saveToFile()
    }

    fun listPlantas(): List<Planta> = plantas

    fun updatePlanta(index: Int, nuevaPlanta: Planta) {
        if (index in plantas.indices) {
            plantas[index] = nuevaPlanta
            saveToFile()
        } else {
            println("Índice no válido.")
        }
    }

    fun deletePlanta(index: Int) {
        if (index in plantas.indices) {
            plantas.removeAt(index)
            saveToFile()
        } else {
            println("Índice no válido.")
        }
    }
}
