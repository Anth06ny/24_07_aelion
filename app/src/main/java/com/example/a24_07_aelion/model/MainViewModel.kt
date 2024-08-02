package com.example.a24_07_aelion.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val LONG_TEXT = """Le Lorem Ipsum est simplement du faux texte employé dans la composition
    et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard
    de l'imprimerie depuis les années 1500"""

fun main() {
    val mainViewModel = MainViewModel()
    mainViewModel.loadWeather("Toulouse")
}

class MainViewModel : ViewModel() {
    var dataList = mutableStateOf<List<PictureBean>>(ArrayList())
    var errorMessage = mutableStateOf("")
    var runInProgress = mutableStateOf(false)

    init {//Création d'un jeu de donnée au démarrage
        loadFakeData()
    }

    fun loadWeather(cityName: String) {

        errorMessage.value = ""
        runInProgress.value = true

        viewModelScope.launch(Dispatchers.Default) {
            try {
                val list = WeatherAPI.loadWeathers(cityName)
                //V1
                dataList.value = list.map { w: WeatherBean ->
                    val icon = w.weather.firstOrNull()?.icon ?: "01d"
                    PictureBean(
                        id = w.id,
                        url = "https://openweathermap.org/img/wn/$icon@4x.png",
                        title = w.name,
                        longText = "Il fait ${w.main.temp}° à ${w.name} (id=${w.id}) avec un vent de ${w.wind.speed} m/s"
                    )
                }
            }
            catch(e:Exception) {
                e.printStackTrace()
                errorMessage.value = e.message ?: "Une erreur est survenue"
            }

            runInProgress.value = false


            //V2
//        dataList.clear()
//        for(w in list) {
//            val icon = w.weather.firstOrNull()?.icon ?: "01d"
//
//            val p = PictureBean(id = w.id,
//                url = "https://openweathermap.org/img/wn/$icon@4x.png",
//                title = w.name,
//                longText = "Il fait ${w.main.temp}° à ${w.name} (id=${w.id}) avec un vent de ${w.wind.speed} m/s"
//                )
//            dataList.add(p)
//        }
        }

    }

    fun loadFakeData() {
        dataList.value = listOf(
            PictureBean(1, "https://picsum.photos/200", "ABCD", LONG_TEXT),
            PictureBean(2, "https://picsum.photos/201", "BCDE", LONG_TEXT),
            PictureBean(3, "https://picsum.photos/202", "CDEF", LONG_TEXT),
            PictureBean(4, "https://picsum.photos/203", "EFGH", LONG_TEXT)
        ).shuffled() //shuffled() pour avoir un ordre différent à chaque appel
    }

}