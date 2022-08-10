package com.example.filmo.ui.composableFunctions


import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import com.example.filmo.R
import com.example.filmo.loadPicture
import com.example.filmo.model.ID
import com.example.filmo.model.PREVSCREEN
import com.example.filmo.model.PREVSCREENDATA
import com.example.filmo.model.Screens
import com.example.filmo.model.dataClass.*
import com.example.filmo.ui.MainActivity
import com.example.filmo.ui.getActivity


// Экран детализации фильма
// Он состоит из верхнего названия
// лента из постера
// и деталей фильма
// которые состоят из названия и деталей
// детали бывают текстом, кнопка? жанры, лист актеров
@Composable
fun Details( bundle: Bundle) {
    val idFilm = bundle.getString(ID) ?: ""
    val mainAct = LocalContext.current.getActivity() as MainActivity
    mainAct.detailVM.loadFilm(idFilm)
    val film = mainAct.detailVM.liveFilm.value

    val lastScreen = bundle.getSerializable(PREVSCREEN) as Screens
    val lastScreenData = bundle.getString(PREVSCREENDATA) ?: ""

    Column {
        Header(film.title?:"", film.year?:"", lastScreen, lastScreenData)
        LazyColumn {
            items(1) {
                Poster(film.poster, film.rating?:"")
                TitleWithText(stringResource(id = R.string.date), film.releaseDate?:"")
                TitleWithText(stringResource(id = R.string.description), film.description?:"")
                TitleWithGenres(film.genres)
                TitleWithActors(actors = film.actors)
            }
        }
    }
}

fun createBundleForDetailsScreen(
    idFilm: String,
    lastScreen: Screens,
    lastScreenData: String
): Bundle {
    return bundleOf(
        Pair(ID, idFilm),
        Pair(PREVSCREEN, lastScreen),
        Pair(PREVSCREENDATA, lastScreenData),
    )
}


// шапка с назанием
@Composable
fun Header(title: String, year: String, lastScreen: Screens, lastScreenData: String) {
    val mainAct = LocalContext.current.getActivity() as MainActivity
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .padding(30.dp, 10.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.arrow_back), contentDescription = "back",
            modifier = Modifier
                .size(40.dp, 25.dp)
                .clickable {
                    mainAct.drawScreen(lastScreen, getBundle(lastScreen, lastScreenData))
                }
        )
        Text(
            text = "$title ($year)", style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(start = 40.dp)
        )
    }
}

fun getBundle(lastScreen: Screens, lastScreenData: String): Bundle {
    return when (lastScreen) {
        Screens.MainScreen -> {
            createBundleForMainScreen(lastScreenData)
        }
        Screens.SelectionScreen -> {
            createBundleForSelectionScreen(lastScreenData)
        }
        else -> {
            bundleOf()
        }
    }
}


// постер с рейтингом и ссылкой на трейлер
@Composable
fun Poster(image: String, rating: String) {
    Box(
        Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp)
            )
            .background(Color.LightGray)
            .padding(vertical = 60.dp), contentAlignment = Alignment.Center
    ) {
        loadPicture(image, R.drawable.image, LocalContext.current).value?.let {
            Image(
                BitmapPainter(it.asImageBitmap()),
                contentDescription = "poster",
                modifier = Modifier
                    .size(240.dp, 360.dp)
                    .clip(RoundedCornerShape(20.dp)),
            )
        }
        Column(
            Modifier.size(240.dp, 360.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.offset(30.dp, (-30).dp)) {
                Image(
                    BitmapPainter(ImageBitmap.imageResource(R.drawable.star)),
                    contentDescription = "rating",
                    modifier = Modifier.size(75.dp, 70.dp),
                )
                Text(
                    text = rating,
                    style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 24.sp)
                )
            }
            Image(
                BitmapPainter(ImageBitmap.imageResource(R.drawable.watch)),
                contentDescription = "trailer",
                modifier = Modifier
                    .padding(20.dp)
                    .size(50.dp)
            )
        }
    }
}

@Composable
fun TitleWithText(title: String, text: String) {
    Column(modifier = Modifier.padding(horizontal = 30.dp)) {
        Subtitle(title)
        Text(text = text, style = MaterialTheme.typography.body1)
    }
}

@Composable
fun TitleWithGenres(genres: List<String?>) {
    Column(modifier = Modifier.padding(horizontal = 30.dp)) {
        Subtitle(stringResource(id = R.string.generes))
        LazyRow() {
            items(genres.size) {
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    modifier = Modifier.padding(end = 20.dp)
                ) {
                    Text(text = genres[it]?:"", style = MaterialTheme.typography.body1)
                }
            }
        }
    }
}


@Composable
fun TitleWithActors(actors: List<Actor>) {
    Column(modifier = Modifier.padding(start = 30.dp, end = 30.dp, bottom = 40.dp)) {
        Subtitle(stringResource(id = R.string.actors))
        LazyRow {
            items(actors.size) {
                // карточка актера
                val actor = actors[it]
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(100.dp)
                        .padding(end = 20.dp)
                ) {
                    loadPicture(actor.image, R.drawable.image, LocalContext.current).value?.let {
                        Image(
                            BitmapPainter(it.asImageBitmap()),
                            contentDescription = actor.name,
                            modifier = Modifier.size(70.dp)
                        )
                    }
                    Text(
                        text = actor.name,
                        style = MaterialTheme.typography.h4,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = actor.asCharacter,
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun Subtitle(title: String) {
    Text(
        text = title, style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
    )
}
