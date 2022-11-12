import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import models.Empaquetador
import models.Fabricador

/* ---Analisis del enunciado---
*
*  IMPORTANTE: El problema lo he adaptado a un productor-consumidor de toda la vida, no con lecturas de
*   de un txt de por medio, ni categorias chungas para cada saco.
* Fabricador (1 productor) [id: String, maxSacos = 300]
* Saco de yeso (producto) [numlote, codId, peso(25-50), fechaProduccion]
*   Tarda en fabricar el saco el tiempo igual al peso del mismo.
*
* Empaquetador (1 consumidor) [id: String], cada 10 sacos genera un lote, cada generacion de lote se espera el tiempo
* de los 10 sacos sumados.
*
* Cada lote se escribira en un txt (parecido al enunciado original)
*
* VERSION CON FLOWS por el 1-1
* */

private const val NUM_PRODUCCION_SACOS = 300
fun main(): Unit = runBlocking {

    FileController.resetFile()

    val fabricador = Fabricador("Manolo", NUM_PRODUCCION_SACOS)
    val empaquetador = Empaquetador("Paco")

    val sacosFlow = fabricador.produceSaco()

    launch {
        empaquetador.preparaLotes(sacosFlow)
    }
}