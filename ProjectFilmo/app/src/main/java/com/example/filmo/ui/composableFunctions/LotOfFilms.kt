package com.example.filmo.ui.composableFunctions

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.filmo.ui.composableFunctions.CardFilmSmall
import com.example.filmo.remote.dataClass.FilmShort

// Здесь функции для второго экрана с детализацией подборок фильмов или результатами поиска
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LotOfFilms(title: String, listFilms: MutableList<FilmShort>) {
    Column(Modifier.padding(20.dp)) {
        Text(
            text = title, style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        LazyVerticalGrid(GridCells.Adaptive(100.dp)) {
            items(listFilms.size) {
                CardFilmSmall(listFilms[it])
            }
        }
    }
}