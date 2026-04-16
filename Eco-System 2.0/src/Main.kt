abstract class SerVivo(val especie: String, val limiteIdade: Int) {
    // Encapsulamento
    protected var energia: Double = 50.0
        set(value) {
            field = value.coerceIn(0.0, 100.0)
        }

    protected var idade: Int = 0
    protected var estaVivo: Boolean = true

    //
    abstract fun executarCiclo()

    protected fun envelhecer() {
        idade++
        if (idade >= limiteIdade || energia <= 0) {
            morrer()
        }
    }

    protected fun gastarEnergia(valor: Double) {
        energia -= valor
    }

    protected fun ganharEnergia(valor: Double) {
        energia += valor
    }

    private fun morrer() {
        estaVivo = false
        println("$especie morreu.")
    }
}

class Planta(especie: String) : SerVivo(especie, 10) {

    override fun executarCiclo() {
        if (!estaVivo) return

        gastarEnergia(2.0)
        ganharEnergia(5.0)
        reagir()
        envelhecer()
        status()
    }

    private fun reagir() {
        if (energia > 70) {
            println("$especie está florescendo")
            gastarEnergia(3.0)
        } else {
            println("$especie está em dormência")
            gastarEnergia(1.0)
        }
    }

    private fun status() {
        println("Planta $especie | Energia: $energia | Idade: $idade")
    }
}

class Animal(especie: String, val ehPredador: Boolean) : SerVivo(especie, 15) {

    override fun executarCiclo() {
        if (!estaVivo) return

        gastarEnergia(5.0)
        gastarEnergia(3.0)

        if (ehPredador) {
            println("$especie está caçando")
            ganharEnergia(10.0)
        } else {
            println("$especie está procurando comida")
            ganharEnergia(6.0)
        }

        reagir()

        envelhecer()
        status()
    }

    fun reagir() {
        if (ehPredador) {
            println("$especie ataca!")
            gastarEnergia(4.0)
        } else {
            println("$especie foge!")
            gastarEnergia(2.0)
        }
    }

    private fun status() {
        println("Animal $especie | Energia: $energia | Idade: $idade")
    }
}

fun main() {
    val ecossistema: List<SerVivo> = listOf(
        Planta("Samambaia"),
        Planta("Cacto"),
        Animal("Leão", true),
        Animal("Coelho", false)
    )

    repeat(20) { ciclo ->
        println("\nCiclo ${ciclo + 1}")
        ecossistema.forEach { it.executarCiclo() }
    }
}