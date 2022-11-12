package models

import FileController
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.distinctUntilChanged

// Consumidor
data class Empaquetador(
    private val id: String
) {
    private var contLote = 1
    private var tiempoProduccion = 0

    private var sacos = mutableListOf<Saco>()

    private var file = FileController.init()

    suspend fun preparaLotes(sacoFlow: Flow<Saco>) {
        sacoFlow.buffer(10).distinctUntilChanged().collect { saco ->
            saco.numLote = contLote
            println("\t -Empaquetador: $id | Recoge -> $saco")
            tiempoProduccion += saco.peso
            sacos.add(saco)
            if (sacos.size == 10) {
                println("\t -Empaquetador: $id | Envia lote $contLote -> $sacos")
                file.appendText("Lote: $contLote \n$sacos\n")
                sacos.clear()
                contLote++
                println("\t -Empaquetador: $id | Tiempo de produccion: $tiempoProduccion ms")
                delay(tiempoProduccion.toLong())
                tiempoProduccion = 0
            }
        }
        println("\t -Empaquetador: $id termino su jornada con un total de ${contLote - 1} lotes")
    }
}
