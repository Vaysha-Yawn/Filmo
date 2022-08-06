package com.example.filmo.ui.composableFunctions

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import com.example.filmo.R
import com.example.filmo.model.SEARCH
import com.example.filmo.model.Screens
import com.example.filmo.model.dataClass.*
import com.example.filmo.ui.MainActivity
import com.example.filmo.ui.exampleData
import com.example.filmo.ui.getActivity

// Здесь функции для первого экрана с подборками фильмов

// главный экран
@Composable
fun MainScreen(
    map: MutableMap<String, MutableList<FilmShort>>,
    bundle: Bundle
) {
    val textState = remember {
        mutableStateOf(bundle.getString(SEARCH) ?: "")
    }

    Column {
        Search(textState)
        if (textState.value == "") {
            SomeCompilation(map)
        } else {
            AnswerForSearch(
                textState.value,
                exampleData.list,
            )
        }
    }
}

fun createBundleForMainScreen(search: String?): Bundle {
    return bundleOf(Pair(SEARCH, search))
}

// Несколько подборок
@Composable
fun SomeCompilation(
    map: MutableMap<String, MutableList<FilmShort>>,
) {
    LazyColumn(Modifier.padding(start = 20.dp, bottom = 20.dp, end = 20.dp)) {
        items(map.size) {
            val pair = map.toList()[it]
            if (it == 0) {
                Compilation(pair.first, pair.second, 0.dp)
            } else {
                Compilation(pair.first, pair.second)
            }
        }
    }
}


// карточка фильма в подборке
@Composable
fun CardFilmSmall(film: FilmShort, inScreens: Screens, screenData: String) {
    val mainAct = LocalContext.current.getActivity() as MainActivity
    Card(
        modifier = Modifier
            .padding(end = 20.dp, top = 20.dp)
            .size(100.dp, 145.dp)
            .clip(RoundedCornerShape(0.dp))
    ) {
        Image(
            BitmapPainter(ImageBitmap.imageResource(R.drawable.image)),
            contentDescription = film.title,
            Modifier
                .fillMaxSize()
                .clickable {
                    mainAct.drawScreen(
                        Screens.DetailsScreen,
                        createBundleForDetailsScreen(film.id, inScreens, screenData)
                    )
                },
            colorFilter = ColorFilter.lighting(Color.Gray, Color.Black),
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
fun Compilation(
    title: String,
    listFilms: MutableList<FilmShort>,
    topSpace: Dp = 40.dp
) {
    val mainAct = LocalContext.current.getActivity() as MainActivity
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
                modifier = Modifier
                    .size(20.dp, 10.dp)
                    .clickable {
                        mainAct.drawScreen(
                            Screens.SelectionScreen,
                            createBundleForSelectionScreen(title)
                        )
                    }
            )
        }
        LazyRow() {
            items(listFilms.size) {
                CardFilmSmall(listFilms[it], Screens.MainScreen, "")
            }
        }
    }
}



