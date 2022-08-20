package com.example.filmo.ui.composableFunctions

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.filmo.model.dataClass.FilmShort
import com.example.filmo.navigation.Screens

// Сетка фильмов
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridFilms(
    listFilms: MutableList<FilmShort>, inScreens: Screens, screenData: String
) {
    LazyVerticalGrid(GridCells.Adaptive(100.dp)) {
        items(listFilms.size) {
            CardFilmSmall(listFilms[it], inScreens, screenData)
        }
    }
}