package com.example.a24_07_aelion.model

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

fun main() {
    val user = MyAPI.loadRandomUser()
    println(
        """
    Il s'appelle ${user.name} pour le contacter :
    Phone : ${user.coord?.phone ?: "-"}
    Mail : ${user.coord?.mail ?: "-"}
""".trimIndent()
    )

    for(user in MyAPI.loadRandomUsers()) {
        println(
            """
    Il s'appelle ${user.name} pour le contacter :
    Phone : ${user.coord?.phone ?: "-"}
    Mail : ${user.coord?.mail ?: "-"}
""".trimIndent()
        )
    }

}

object MyAPI {
    val client = OkHttpClient()
    val gson = Gson()

    fun loadRandomUser(): MyUserBean {
        val json = sendGet("https://www.amonteiro.fr/api/randomuser")

        //Parsing
        return gson.fromJson(json, MyUserBean::class.java)
    }

    fun loadRandomUsers(): List<MyUserBean> {
        val json = sendGet("https://www.amonteiro.fr/api/randomusers")

        //Parsing
        return gson.fromJson(json, Array<MyUserBean>::class.java).toList()
    }

    //Méthode qui prend en entrée une url, execute la requête
    //Retourne le code HTML/JSON reçu
    fun sendGet(url: String): String {
        println("url : $url")
        //Création de la requête
        val request = Request.Builder().url(url).build()
        //Execution de la requête
        return client.newCall(request).execute().use { //it:Response
            //use permet de fermer la réponse qu'il y ait ou non une exception
            //Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}\n${it.body.string()}")
            }
            //Résultat de la requête
            it.body.string()
        }
    }

}

data class MyUserBean(var name: String, var age: Int, var coord: PhoneBean?)
data class PhoneBean(var phone: String?, var mail: String?)








