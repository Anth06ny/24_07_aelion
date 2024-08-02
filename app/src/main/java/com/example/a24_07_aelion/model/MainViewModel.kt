package com.example.a24_07_aelion.model

import androidx.lifecycle.ViewModel

const val LONG_TEXT = """Le Lorem Ipsum est simplement du faux texte employé dans la composition
    et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard
    de l'imprimerie depuis les années 1500"""



class MainViewModel : ViewModel() {
    var dataList: List<PictureBean> =  ArrayList()
    var errorMessage = ""
    var runInProgress = false

    init {//Création d'un jeu de donnée au démarrage
        loadFakeData()
    }

    fun loadFakeData(){
        dataList = listOf(PictureBean(1, "https://picsum.photos/200", "ABCD", LONG_TEXT),
            PictureBean(2, "https://picsum.photos/201", "BCDE", LONG_TEXT),
            PictureBean(3, "https://picsum.photos/202", "CDEF", LONG_TEXT),
            PictureBean(4, "https://picsum.photos/203", "EFGH", LONG_TEXT)
        ).shuffled() //shuffled() pour avoir un ordre différent à chaque appel
    }

}