package com.example.filmo.ui.composableFunctions

import android.os.Bundle
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import com.example.filmo.MainActivity
import com.example.filmo.R
import com.example.filmo.getActivity
import com.example.filmo.ui.composableFunctions.CardFilmSmall
import com.example.filmo.remote.dataClass.FilmShort
import com.example.filmo.remote.dataClass.SEARCH
import com.example.filmo.remote.dataClass.Screens
import com.example.filmo.remote.dataClass.TITLE

// Второй экран - подборка фильмов
@Composable
fun SelectionScreen(listFilms: MutableList<FilmShort>, bundle: Bundle) {

    val title = bundle.getString(TITLE)?:""
    val mainAct = LocalContext.current.getActivity() as MainActivity
    Column(Modifier.padding(20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                BitmapPainter(ImageBitmap.imageResource(R.drawable.arrow)),
                contentDescription = "arrow",
                modifier = Modifier
                    .size(20.dp, 10.dp)
                    .clickable {
                        mainAct.drawScreen(Screens.MainScreen, createBundleForMainScreen(""))
                    }
            )
            Text(text = title, style = MaterialTheme.typography.subtitle1 ,
                modifier = Modifier.padding(start = 20.dp))
        }
        GridFilms(listFilms, Screens.SelectionScreen, title)
    }
}

fun createBundleForSelectionScreen(title:String):Bundle{
    return bundleOf(Pair(TITLE, title))
}


// Экран с названием поиска и его результами
@Composable
fun AnswerForSearch(
    searchText: String, listFilms: MutableList<FilmShort>
) {
    Column(Modifier.padding(20.dp)) {
        Text(
            text = stringResource(id = R.string.answers) + " " + searchText, style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        GridFilms(listFilms, Screens.MainScreen, searchText)
    }
}

// Сетка фильмов
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridFilms(
    listFilms: MutableList<FilmShort>, inScreens: Screens, screenData:String
) {
    LazyVerticalGrid(GridCells.Adaptive(100.dp)) {
        items(listFilms.size) {
            CardFilmSmall(listFilms[it], inScreens, screenData)
        }
    }

}