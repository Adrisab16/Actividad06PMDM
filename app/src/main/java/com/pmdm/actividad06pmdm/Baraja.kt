package com.pmdm.actividad06pmdm

/**
 * Clase baraja
 * @property listaCartas lista de cartas de la baraja
 * @property tamanio indica la cantidad de cartas de la baraja
 * @see Carta
 */
class Baraja {
    companion object{
        val listaCartas : ArrayList<Carta> = arrayListOf()
        var tamanio = 0
        init {
            creaBaraja()
            barajar()
        }

        /**
         * Crea la baraja de cartas desde las clases Naipes y Palos
         * @see Naipes
         * @see Palos
         */
        fun creaBaraja(){
            listaCartas.clear()
            for(i in Palos.values()){
                for(x in Naipes.values()){
                    val micarta = Carta(x,i,0,0,0)
                    listaCartas.add(micarta)
                }
            }
            tamanio = listaCartas.size
        }

        /**
         * Baraja las cartas
         */
        fun barajar(){
            listaCartas.shuffle()
        }
        /**
         * Saca una carta de la baraja
         * @return devuleve una carta de la baraja
         */
        fun cogerCarta():Carta?{
            if (listaCartas.size>0) {
                val cartaTemp = listaCartas.last()
                listaCartas.remove(cartaTemp)
                tamanio = listaCartas.size
                return cartaTemp
            }
            return null
        }
    }
}