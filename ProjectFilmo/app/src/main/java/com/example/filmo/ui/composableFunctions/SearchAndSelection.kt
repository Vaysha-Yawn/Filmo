package com.example.filmo.ui.composableFunctions

import android.os.Bundle
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import com.example.filmo.R
import com.example.filmo.model.Screens
import com.example.filmo.model.TITLE
import com.example.filmo.model.dataClass.FilmShort
import com.example.filmo.ui.MainActivity
import com.example.filmo.ui.exampleData
import com.example.filmo.ui.getActivity

// Второй экран - подборка фильмов
@Composable
fun SelectionScreen( bundle: Bundle) {

    val mainAct = LocalContext.current.getActivity() as MainActivity

    val title = bundle.getString(TITLE)?:""

    when (title){
        stringResource(id = R.string.Top250Movies)->{
            mainAct.selectionVM.loadTop250()
        }
        stringResource(id = R.string.MostPopularMovies)->{
            mainAct.selectionVM.loadMostPopularMovies()
        }
        stringResource(id = R.string.InTheaters)->{
            mainAct.selectionVM.loadInTheaters()
        }
        stringResource(id = R.string.ComingSoon)->{
            mainAct.selectionVM.loadComingSoon()
        }
    }

    Column(Modifier.padding(20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 20.dp)) {
            Image(
                BitmapPainter(ImageBitmap.imageResource(R.drawable.arrow)),
                contentDescription = "arrow",
                modifier = Modifier
                    .rotate(180f)
                    .size(20.dp, 10.dp)
                    .clickable {
                        mainAct.drawScreen(Screens.MainScreen, createBundleForMainScreen(""))
                    }
            )
            Text(text = title, style = MaterialTheme.typography.subtitle1 ,
                modifier = Modifier.padding(start = 20.dp))
        }
        GridFilms(mainAct.selectionVM.liveList.value.toMutableList(), Screens.SelectionScreen, title)
    }
}

fun createBundleForSelectionScreen(title:String):Bundle{
    return bundleOf(Pair(TITLE, title))
}


// Экран с названием поиска и его результами
@Composable
fun AnswerForSearch(
    searchText: String
) {
    val mainAct = LocalContext.current.getActivity() as MainActivity
    mainAct.searchVM.loadSearch(searchText)
    val listFilms = mainAct.searchVM.liveFilm.value.toMutableList()

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

// Виджет поиска
@Composable
fun Search(state: MutableState<String>) {
    TextField(
        value = state.value,
        onValueChange = { state.value = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        textStyle = TextStyle(
            fontSize = 20.sp
        ),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "search",
                Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != "") {
                IconButton(onClick = {
                    state.value = ""
                }) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "search",
                        Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )

                }
            }
        },
        placeholder = {
            Text(
                text = stringResource(R.string.search), style = TextStyle(
                    fontSize = 20.sp,
                )
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(30.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.DarkGray,
            leadingIconColor = Color.Gray,
            trailingIconColor = Color.Gray,
            backgroundColor = Color.LightGray,
            focusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Gray,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            placeholderColor = Color.Gray,
        )
    )
}