package com.example.a24_07_aelion.exo

import com.example.a24_07_aelion.PRIX_BAGUETTE
import com.example.a24_07_aelion.PRIX_CROISSANT
import com.example.a24_07_aelion.PRIX_SANDWITCH
import com.example.a24_07_aelion.model.CarBean


data class UserBean(var name: String, var surname: String, var car1: CarBean)
data class CarBean(var name: String, var marque: String)


fun main() {


}


fun name(p1: String = "", p2: String = "toto"): String = p1


fun scanText(question: String): String {
    print(question)
    return readlnOrNull() ?: "-"
}

fun scanNumber(question: String): Int {
    print(question)
    return readlnOrNull()?.toIntOrNull() ?: 0
}

fun boulangerie(nbCroissant: Int = 0, nbSandwitch: Int = 0, nbBaguette: Int = 0) =
    nbCroissant * PRIX_CROISSANT + nbSandwitch * PRIX_SANDWITCH + PRIX_BAGUETTE * nbBaguette

fun pair(c: Int) = c % 2 == 0
fun myPrint(text: String) = println("#$text#")

fun min(a: Int, b: Int, c: Int) = if (a < b && a < c) a
else if (b < a && b < c) b
else c










































