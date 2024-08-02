package com.example.a24_07_aelion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.a24_07_aelion.model.MainViewModel
import com.example.a24_07_aelion.ui.screens.SearchScreen
import com.example.a24_07_aelion.ui.theme._24_07_aelionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            _24_07_aelionTheme {
                val mainViewModel = MainViewModel()
                SearchScreen(mainViewModel)
            }
        }
    }
}