package com.example.filmo.ui.composableFunctions

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.filmo.R
import com.example.filmo.navigation.Screens
import com.example.filmo.navigation.TITLE
import com.example.filmo.navigation.getBundleForMainScreen
import com.example.filmo.retrofit.RetrofitApp
import com.example.filmo.tools.getActivity
import com.example.filmo.ui.MainActivity


// Второй экран - подборка фильмов
@Composable
fun SelectionScreen(bundle: Bundle) {

    val mainAct = LocalContext.current.getActivity() as MainActivity
    val api = (mainAct.application as RetrofitApp).movieApi

    val title = bundle.getString(TITLE) ?: ""

    when (title) {
        stringResource(id = R.string.Top250Movies) -> {
            mainAct.selectionVM.loadTop250(api, mainAct.key.value)
        }
        stringResource(id = R.string.MostPopularMovies) -> {
            mainAct.selectionVM.loadMostPopularMovies(api, mainAct.key.value)
        }
        stringResource(id = R.string.InTheaters) -> {
            mainAct.selectionVM.loadInTheaters(api, mainAct.key.value)
        }
        stringResource(id = R.string.ComingSoon) -> {
            mainAct.selectionVM.loadComingSoon(api, mainAct.key.value)
        }
    }

    Column(Modifier.padding(20.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            Image(
                BitmapPainter(ImageBitmap.imageResource(R.drawable.arrow)),
                contentDescription = "arrow",
                modifier = Modifier
                    .rotate(180f)
                    .size(20.dp, 10.dp)
                    .clickable {
                        mainAct.navigate(Screens.MainScreen, getBundleForMainScreen(""))
                    }
            )
            Text(
                text = title, style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(start = 20.dp)
            )
        }
        GridFilms(
            mainAct.selectionVM.liveList.value.toMutableList(),
            Screens.SelectionScreen,
            title
        )
    }
}

