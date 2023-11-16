package com.pmdm.actividad06pmdm

/**
 * Clase baraja, donde podremos crear una baraja nueva, podremos barajar las cartas y mostrarlas:
 */
class Baraja {
    companion object{ // Usamos el Companion Object para no tener que inicializar las variablesq directamente en el constructor primario
        val cardlist : ArrayList<Carta> = arrayListOf()
        var size = 0
        init {newDeck();shuffle()}

        /**
         * Crea la baraja de cartas usando las clases creadas (naipes y palos):
         */
        fun newDeck(){
            cardlist.clear() // Eliminamos lo que pueda haber en la lista de cartas
            for(v in Palos.values()){
                for(j in Naipes.values()){
                    val card = Carta(j,v,0,0,0)
                    cardlist.add(card) // Vamos rellenando la lista con las cartas
                }
            }
            size = cardlist.size // Actualizamos el tamaño de la lista
        }

        /**
         * Esta función barajará las cartas (Desordenará las cartas de la lista de cartas de la baraja):
         */
        fun shuffle(){cardlist.shuffle()}

        /**
         * Esta función mostrará una carta de la baraja si la baraja tiene como mínimo 1 carta:
         */
        fun cogerCarta():Carta?{
            if (cardlist.size>0){
                val cardshown = cardlist.last() // Sacará la última carta de la lista
                cardlist.remove(cardshown) // Eliminará la última de la lista para que no se repita despues
                size = cardlist.size // Actualiza el tamaño de la lista de cartas
                return cardshown
            }
            return null
        }
    }
}