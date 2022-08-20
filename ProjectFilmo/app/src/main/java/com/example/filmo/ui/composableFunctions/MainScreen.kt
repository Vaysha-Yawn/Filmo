package com.example.filmo.ui.composableFunctions

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.filmo.R
import com.example.filmo.model.dataClass.FilmShort
import com.example.filmo.navigation.SEARCH
import com.example.filmo.navigation.Screens
import com.example.filmo.navigation.getBundleForSelectionScreen
import com.example.filmo.retrofit.RetrofitApp
import com.example.filmo.tools.getActivity
import com.example.filmo.ui.MainActivity


// Здесь функции для первого экрана с подборками фильмов

// первый экран, несколько кратких подборок фльмов, сверху строка поиска
@Composable
fun MainScreen(
    bundle: Bundle
) {
    val textState = remember {
        mutableStateOf(bundle.getString(SEARCH) ?: "")
    }

    Column {
        Search(textState)
        if (textState.value == "") {

            val mainAct = LocalContext.current.getActivity() as MainActivity

            //val bool = mainAct.mainVM.loadAll((mainAct.application as RetrofitApp).movieApi, mainAct.key.value)
            val bool = mainAct.mainVM.loadTop250((mainAct.application as RetrofitApp).movieApi, mainAct.key.value)
            if (bool==false){
                mainAct.nextKey()
            }

            val listTop250 = mainAct.mainVM.liveTop250.value
            val titleTop250 = stringResource(id = R.string.Top250Movies)

            val listComingSoon = mainAct.mainVM.liveComingSoon.value
            val titleComingSoon = stringResource(id = R.string.ComingSoon)

            val listInTheaters = mainAct.mainVM.liveInTheaters.value
            val titleInTheaters = stringResource(id = R.string.InTheaters)

            val listMostPopularMovies = mainAct.mainVM.liveMostPopularMovies.value
            val titleMostPopularMovies = stringResource(id = R.string.MostPopularMovies)

            SomeCompilation(
                mutableMapOf<String, List<FilmShort>>(
                    titleTop250 to listTop250,
                    titleMostPopularMovies to listMostPopularMovies,
                    titleInTheaters to listInTheaters,
                    titleComingSoon to listComingSoon,
                )
            )
        } else {
            ResultForSearch(
                textState.value,
            )
        }
    }
}

// Несколько подборок
@Composable
fun SomeCompilation(
    map: MutableMap<String, List<FilmShort>>,
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


// краткий вид подбоки фильмов со стрелкой-ссылкой на подробнее
@Composable
fun Compilation(
    title: String,
    listFilms: List<FilmShort>,
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
                        mainAct.navigate(
                            Screens.SelectionScreen,
                            getBundleForSelectionScreen(title)
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



