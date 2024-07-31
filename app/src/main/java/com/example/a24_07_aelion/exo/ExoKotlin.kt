package com.example.a24_07_aelion.exo

import com.example.a24_07_aelion.PRIX_BAGUETTE
import com.example.a24_07_aelion.PRIX_CROISSANT
import com.example.a24_07_aelion.PRIX_SANDWITCH


var toto = 5

fun main() {
    println(boulangerie(5))
    println(boulangerie(5, nbBaguette = 3))

    println(scanNumber("Donnez un nombre : "))

    var tata = 4
    println("Fin")
}

fun scanText(question:String): String {
    print(question)
    return readlnOrNull() ?: "-"
}

fun scanNumber(question:String) : Int {
    print(question)
    return readlnOrNull()?.toIntOrNull() ?: 0
}

fun boulangerie(nbCroissant : Int = 0 , nbSandwitch : Int = 0, nbBaguette : Int = 0)
    =  nbCroissant * PRIX_CROISSANT + nbSandwitch * PRIX_SANDWITCH + PRIX_BAGUETTE * nbBaguette

fun pair(c:Int) = c%2 == 0
fun myPrint(text:String) = println("#$text#")

fun min(a: Int, b: Int, c: Int) = if (a < b && a < c) a
else if (b < a && b < c) b
else c










































