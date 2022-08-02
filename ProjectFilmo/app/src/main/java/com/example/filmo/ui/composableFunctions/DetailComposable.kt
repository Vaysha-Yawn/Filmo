package com.example.filmo.ui.composableFunctions


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.filmo.R
import com.example.filmo.remote.dataClass.FilmMore


// Экран детализации фильма
// Он состоит из верхнего названия
// лента из постера
// и деталей фильма
// которые состоят из названия и деталей
// детали бывают текстом, кнопка? жанры, лист актеров
@Composable
fun Details(film: FilmMore) {
    Column {
        Header(film.title, film.year)
        LazyColumn {
            items(1) {
                Poster(R.drawable.image, film.rating)

            }
        }
    }
}

// шапка с назанием
@Composable
fun Header(title: String, year: String) {
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
            modifier = Modifier.size(40.dp, 25.dp)
        )
        Text(
            text = "$title ($year)", style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(start = 40.dp)
        )
        //Spacer(modifier = Modifier.size(40.dp, 0.dp))
    }
}

// шапка с назанием
@Composable
fun Poster(posterId: Int, rating: String) {
    Box(
        Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp)
            )
            .background(Color.LightGray)
            .padding(vertical = 60.dp), contentAlignment = Alignment.Center
    ) {
        Image(
            BitmapPainter(ImageBitmap.imageResource(posterId)),
            contentDescription = "poster",
            modifier = Modifier
                .size(240.dp, 360.dp)
                .clip(RoundedCornerShape(20.dp)),
        )
        Column(Modifier.size(240.dp, 360.dp), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.End) {
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
                    .padding(20.dp).size(50.dp)
            )
        }
    }
}
