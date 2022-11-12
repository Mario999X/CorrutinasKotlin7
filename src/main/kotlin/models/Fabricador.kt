package models

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

// Productor
data class Fabricador(
    private val id: String,
    private val numSacos: Int
) {
    fun produceSaco() = flow {
        for (i in 1..numSacos) {
            val saco = Saco(i)
            println("Fabricador: $id | Saco: $i")
            emit(saco)
            delay(saco.peso.toLong())
        }
        println(" ---Fabricador: $id termino su jornada---")
    }
}
