package org.example

import org.example.CRUD.Crud
import org.example.Identidades.Organo
import org.example.Identidades.Planta
import java.util.Scanner

fun main() {
    val crud = Crud()
    val scanner = Scanner(System.`in`)

    while (true) {
        println("==== Menú Principal ====")
        println("1. Gestionar Plantas")
        println("2. Salir")
        print("Selecciona una opción: ")

        when (scanner.nextLine().toIntOrNull()) {
            1 -> gestionarPlantas(crud, scanner)
            2 -> {
                println("Saliendo del sistema. ¡Adiós!")
                break
            }
            else -> println("Opción no válida.")
        }
    }
}

fun gestionarPlantas(crud: Crud, scanner: Scanner) {
    while (true) {
        println("==== Menú de Plantas ====")
        println("1. Agregar Planta")
        println("2. Listar Plantas")
        println("3. Actualizar Planta")
        println("4. Eliminar Planta")
        println("5. Regresar al Menú Principal")
        print("Selecciona una opción: ")

        when (scanner.nextLine().toIntOrNull()) {
            1 -> agregarPlanta(crud, scanner)
            2 -> listarPlantas(crud)
            3 -> actualizarPlanta(crud, scanner)
            4 -> eliminarPlanta(crud, scanner)
            5 -> break
            else -> println("Opción no válida.")
        }
    }
}

fun agregarPlanta(crud: Crud, scanner: Scanner) {
    print("Nombre: ")
    val nombre = scanner.nextLine()
    print("Altura (cm): ")
    val altura = scanner.nextLine().toIntOrNull() ?: 0
    print("Tipo: ")
    val tipo = scanner.nextLine()
    print("¿Es perenne? (true/false): ")
    val esPerenne = scanner.nextLine().toBoolean()

    val organos = mutableListOf<Organo>()
    println("¿Deseas agregar órganos? (si/no): ")
    if (scanner.nextLine().equals("si", true)) {
        do {
            organos.add(agregarOrgano(scanner))
            print("¿Agregar otro órgano? (si/no): ")
        } while (scanner.nextLine().equals("si", true))
    }

    crud.addPlanta(Planta(nombre, altura, tipo, esPerenne, organos))
    println("Planta agregada.")
}

fun listarPlantas(crud: Crud) {
    val plantas = crud.listPlantas()
    if (plantas.isEmpty()) {
        println("No hay plantas registradas.")
    } else {
        plantas.forEachIndexed { index, planta ->
            println("$index: $planta")
        }
    }
}

fun actualizarPlanta(crud: Crud, scanner: Scanner) {
    listarPlantas(crud)
    print("Índice de planta a actualizar: ")
    val index = scanner.nextLine().toIntOrNull()

    if (index != null && index in crud.listPlantas().indices) {
        val plantaActual = crud.listPlantas()[index]

        println("Actualizando la planta con índice $index. Presiona Enter para mantener el valor actual.")

        print("Nuevo nombre (actual: ${plantaActual.nombre}): ")
        val nuevoNombre = scanner.nextLine().takeIf { it.isNotEmpty() } ?: plantaActual.nombre

        print("Nueva altura (en cm, actual: ${plantaActual.altura}): ")
        val nuevaAltura = scanner.nextLine().toIntOrNull() ?: plantaActual.altura

        print("Nuevo tipo (actual: ${plantaActual.tipo}): ")
        val nuevoTipo = scanner.nextLine().takeIf { it.isNotEmpty() } ?: plantaActual.tipo

        print("¿Es perenne? (true/false, actual: ${plantaActual.esPerenne}): ")
        val nuevoEsPerenne = scanner.nextLine().toBooleanStrictOrNull() ?: plantaActual.esPerenne

        // Actualizar los órganos
        println("¿Deseas actualizar los órganos? (si/no, actual: ${plantaActual.organos.size} órganos): ")
        val nuevosOrganos = if (scanner.nextLine().equals("si", ignoreCase = true)) {
            val organos = mutableListOf<Organo>()
            println("Ingrese los órganos uno por uno:")
            while (true) {
                organos.add(agregarOrgano(scanner))
                println("¿Deseas agregar otro órgano? (si/no): ")
                if (!scanner.nextLine().equals("si", ignoreCase = true)) break
            }
            organos
        } else {
            plantaActual.organos
        }

        // Crear la nueva planta actualizada
        val nuevaPlanta = plantaActual.copy(
            nombre = nuevoNombre,
            altura = nuevaAltura,
            tipo = nuevoTipo,
            esPerenne = nuevoEsPerenne,
            organos = nuevosOrganos.toMutableList()
        )

        crud.updatePlanta(index, nuevaPlanta)
        println("Planta actualizada exitosamente.")
    } else {
        println("Índice inválido.")
    }
}


fun eliminarPlanta(crud: Crud, scanner: Scanner) {
    listarPlantas(crud)
    print("Índice de planta a eliminar: ")
    val index = scanner.nextLine().toIntOrNull()

    if (index != null && index in crud.listPlantas().indices) {
        crud.deletePlanta(index)
        println("Planta eliminada.")
    } else {
        println("Índice inválido.")
    }
}

fun agregarOrgano(scanner: Scanner): Organo {
    print("Nombre: ")
    val nombre = scanner.nextLine()
    print("Peso (g): ")
    val peso = scanner.nextLine().toDoubleOrNull() ?: 0.0
    print("Color: ")
    val color = scanner.nextLine()
    print("¿Es comestible? (true/false): ")
    val esComestible = scanner.nextLine().toBoolean()
    print("Descripción: ")
    val descripcion = scanner.nextLine()
    return Organo(nombre, peso, color, esComestible, descripcion)
}
