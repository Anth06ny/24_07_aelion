package com.example.a24_07_aelion.model

import java.util.Random


fun main() {
   var car = CarBean("","")
   var car2 = CarBean("","")
}

class RandomName {

    private var list = arrayListOf("Toto", "Tata", "Titi")

    private var oldValue = ""

    fun nextDiff(): String {
        var newValue = next()
        while(newValue == oldValue) {
            newValue = next()
        }

        oldValue = newValue
        return oldValue
    }

    fun addAll(vararg names: String) {
        for (name in names) {
            add(name)
        }
    }

    fun add(name: String?) {
        if (name !in list && !name.isNullOrBlank()) {
            list.add(name)
        }
    }

    fun next() = list.random()

}

class ThermometerBean(val min: Int, val max: Int, value: Int) {
    var value = value.coerceIn(min, max)
        set(newValue) {
            field = newValue.coerceIn(min, max)
        }
}

class PrintRandomIntBean(val max: Int) {
    private val random = Random()

    init {
        println(random.nextInt(max))
        println(random.nextInt(max))
        println(random.nextInt(max))
    }

    constructor() : this(100) {
        println(random.nextInt(max))
    }


}

class HouseBean(var color: String, length: Int, width: Int) {
    var area = length * width

    fun print() = println("La maison $color fait ${area}m²")
}

data class CarBean(var marque: String, var model: String?) {
    var color = ""

    fun print() = println("Une $marque $model de couleur $color")
}