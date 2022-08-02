package com.example.filmo.ui.composableFunctions

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.filmo.R
import com.example.filmo.remote.dataClass.FilmShort

// Здесь функции для первого экрана с подборками фильмов

// карточка фильма в подборке
@Composable
fun CardFilmSmall(film: FilmShort) {
    Card(
        modifier = Modifier
            .padding(end = 20.dp, top = 20.dp)
            .size(100.dp, 145.dp)
            .clip(RoundedCornerShape(0.dp))
    ) {
        Image(
            BitmapPainter(ImageBitmap.imageResource(R.drawable.image)),
            contentDescription = film.title,
            Modifier.fillMaxSize(),
            colorFilter = ColorFilter.lighting(Color.Gray, Color.Black)
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(
                text = film.title,
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(10.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = film.year,
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.padding(10.dp)
                )
                Text(
                    text = film.rating,
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}


// Название подборки, стрелка - ссылка на отображение всей подборки
// И список фильмов в подборке
@Composable
fun Compilation(title: String, listFilms: MutableList<FilmShort>, topSpace: Dp = 40.dp) {
    Column(modifier = Modifier.padding(top = topSpace)) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            Alignment.CenterVertically
        ) {
            Text(text = title, style = MaterialTheme.typography.subtitle2)
            Image(
                BitmapPainter(ImageBitmap.imageResource(R.drawable.arrow)),
                contentDescription = "arrow",
                modifier = Modifier.size(20.dp, 10.dp)
            )
        }
        LazyRow() {
            items(listFilms.size) {
                CardFilmSmall(listFilms[it])
            }
        }
    }
}

// Несколько подборок
@Composable
fun MainAct(map: MutableMap<String, MutableList<FilmShort>>) {
    LazyColumn(Modifier.padding(start = 20.dp, bottom = 20.dp, end = 20.dp)) {
        items(map.size) {
            val pair = map.toList()[it]
            if (it == 0){
                Compilation(pair.first, pair.second, 0.dp)
            }else{
                Compilation(pair.first, pair.second)
            }
        }
    }
}


