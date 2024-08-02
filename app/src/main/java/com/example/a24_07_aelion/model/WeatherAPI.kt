package com.example.a24_07_aelion.model

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request


object WeatherAPI {
    val client = OkHttpClient()
    val gson = Gson()

    fun loadWeathers(cityName: String): ArrayList<WeatherBean> {
        val json = sendGet("https://api.openweathermap.org/data/2.5/find?q=$cityName&cnt=5&appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr")

        //Parsing
        val weather = gson.fromJson(json, WeatherResultBean::class.java)

        return weather.list
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

data class WeatherResultBean(var list: ArrayList<WeatherBean>)
data class WeatherBean(var id: Int, var name: String, var main: TempBean, var wind: WindBean, var weather : List<DescriptionBean>)
data class TempBean(var temp: Double)
data class WindBean(var speed: Double)
data class DescriptionBean(var description: String, var icon:String )


//Utilisation
fun main() {
    //Lance la requête et met le corps de la réponse dans html
    val result = WeatherAPI.loadWeathers("Nice")

    val first = result[0]

    println("Il fait ${first.main.temp}° à ${first.name} (id=${first.id}) avec un vent de ${first.wind.speed} m/s")

}

