package com.example.a24_07_aelion.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.a24_07_aelion.R
import com.example.a24_07_aelion.model.MainViewModel
import com.example.a24_07_aelion.model.PictureBean
import com.example.a24_07_aelion.ui.theme._24_07_aelionTheme

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES, locale = "fr")
@Composable
fun SearchScreenPreview() {
    //Il faut remplacer NomVotreAppliTheme par le thème de votre application
    //Utilisé par exemple dans MainActivity.kt sous setContent {...}
    _24_07_aelionTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            val mainViewModel = MainViewModel()
            SearchScreen(mainViewModel)
        }
    }
}


@Composable
fun SearchScreen(mainViewModel: MainViewModel) {
    Column(modifier = Modifier.background(Color.LightGray)) {

        //seacherbar
        SearchBar()

        //Permet de remplacer très facilement le RecyclerView. LazyRow existe aussi
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.weight(1f)) {
            items(mainViewModel.dataList.size) {
                PictureRowItem(data = mainViewModel.dataList[it]
                )
            }
        }

        //boutons
        Row(modifier = Modifier.background(Color.Yellow).fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            Button(
                onClick = { /* Do something! */ },
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding
            ) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Like")
            }

            Button(
                onClick = { /* Do something! */ },
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding
            ) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(stringResource(R.string.bt_ok))
            }
        }
    }
}

@Composable
fun SearchBar(modifier:Modifier = Modifier){
    TextField(
        value = "Coucou", //Valeur affichée
        onValueChange = {newValue:String -> }, //Nouveau texte entrée
        leadingIcon = { //Image d'icone
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        },
        singleLine = true,
        label = { Text("Enter text") }, //Texte d'aide qui se déplace
        placeholder = { //Texte d'aide qui disparait
            //Pour aller le chercher dans string.xml
            //Text(stringResource(R.string.placeholder_search))
            //En dur
            Text("Recherche")
        },
        //Comment le composant doit se placer
        modifier = modifier
            .fillMaxWidth() // Prend toute la largeur
            .heightIn(min = 56.dp) //Hauteur minimum
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PictureRowItem(modifier: Modifier = Modifier, data: PictureBean) {

    Row (modifier = Modifier.fillMaxWidth().background(Color.White)) {
        //Permission Internet nécessaire
        GlideImage(
            model = data.url,
            //Pour aller le chercher dans string.xml
            //contentDescription = getString(R.string.picture_of_cat),
            //En dur
            contentDescription = "une photo de chat",
            // Image d'attente. Permet également de voir l'emplacement de l'image dans la Preview
            loading = placeholder(R.mipmap.ic_launcher_round),
            // Image d'échec de chargement
            failure = placeholder(R.mipmap.ic_launcher),
            contentScale = ContentScale.Fit,
            //même autres champs qu'une Image classique
            modifier = Modifier.heightIn(max = 100.dp) //Sans hauteur il prendra tous l'écran
                .widthIn(max= 100.dp)
        )
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = data.title,
                fontSize = 20.sp,
            )
            Text(
                text = data.longText.take(20) +"...",
                fontSize = 14.sp,
                color = Color.Blue,
            )

        }


    }


}