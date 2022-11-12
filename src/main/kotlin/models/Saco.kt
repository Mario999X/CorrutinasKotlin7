package models

import java.time.LocalDate

// Producto
data class Saco(
    private val id: Int,
    val peso: Int = (25..50).random(),
    var numLote: Int = 0,
    private val fechaProduccion: String = LocalDate.now().toString()
) {
    override fun toString(): String {
        return "Saco(id=$id, peso=$peso, numLote=$numLote, fechaProduccion='$fechaProduccion')"
    }
}
