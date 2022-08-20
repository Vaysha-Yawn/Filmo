package com.example.filmo.ui.composableFunctions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.filmo.R
import com.example.filmo.model.dataClass.FilmShort
import com.example.filmo.navigation.Screens
import com.example.filmo.navigation.getBundleForDetailsScreen
import com.example.filmo.tools.getActivity
import com.example.filmo.ui.MainActivity

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
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(film.poster)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.cardfilmshort),
            contentDescription = film.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    mainAct.navigate(
                        Screens.DetailsScreen,
                        getBundleForDetailsScreen(film.id, inScreens, screenData)
                    )
                },
            colorFilter = ColorFilter.lighting(Color.Gray, Color.Black),
        )

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(
                text = film.title ?: "",
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(10.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = film.year ?: "",
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.padding(10.dp)
                )
                Text(
                    text = film.rating ?: "",
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}
