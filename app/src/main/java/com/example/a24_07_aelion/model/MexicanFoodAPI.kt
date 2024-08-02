package com.example.a24_07_aelion.model

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request


object MexicanFoodAPI {
    val client = OkHttpClient()
    val gson = Gson()

    fun loadMexicanFood(): List<MexicanFoodResultBeanItem> {
        val json = sendGet("/")

        //Parsing
        return gson.fromJson(json, Array<MexicanFoodResultBeanItem>::class.java).toList()
    }

    //Méthode qui prend en entrée une url, execute la requête
    //Retourne le code HTML/JSON reçu
    fun sendGet(url: String): String {
        val finalURL = "https://the-mexican-food-db.p.rapidapi.com$url"
        println("url : $finalURL")
        //Création de la requête
        val request = Request.Builder()
            .url(finalURL)
            .get()
            .addHeader("x-rapidapi-key", "93329c7cf9msha136bd696cd1040p10a1dejsnbc52cdb0746e")
            .addHeader("x-rapidapi-host", "the-mexican-food-db.p.rapidapi.com")
            .build()
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


data class MexicanFoodResultBeanItem(
    val difficulty: String,
    val id: String,
    val image: String,
    val title: String
)

//Utilisation
fun main() {
   val res = MexicanFoodAPI.loadMexicanFood()

    for (mex in res) {
        println("-${mex.title} : ${mex.difficulty} ${mex.image}")
    }
}

