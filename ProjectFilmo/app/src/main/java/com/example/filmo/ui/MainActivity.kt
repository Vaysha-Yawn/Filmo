package com.example.filmo.ui

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.example.filmo.MainVM
import com.example.filmo.model.Screens
import com.example.filmo.model.dataClass.FilmShort
import com.example.filmo.model.remote.dataClass.Top250DataDetail
import com.example.filmo.model.testData.TestData
import com.example.filmo.ui.composableFunctions.*
import com.example.filmo.ui.theme.FilmoTheme
import java.lang.IllegalStateException


val exampleData = TestData()

class MainActivity : ComponentActivity() {

    val viewModel:MainVM by viewModels()
    lateinit var nav: MutableState<Screens>
    var arguments = bundleOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FilmoTheme {
                nav = remember {
                    mutableStateOf(Screens.MainScreen)
                }
                DrawScreen(nav.value, arguments)
            }
        }
    }

    fun drawScreen(nameScreen: Screens, arguments: Bundle) {
        this.arguments = arguments
        nav.value = nameScreen
    }

    fun loadTop250(){
        viewModel.loadTop250()
    }

    fun loadMostPopularMovies(){
        viewModel.loadMostPopularMovies()
    }

    fun loadInTheaters(){
        viewModel.loadInTheaters()
    }

    fun loadComingSoon(){
        viewModel.loadComingSoon()
    }


}

@Composable
fun DrawScreen(nameScreen: Screens, arguments: Bundle) {
    when (nameScreen) {
        Screens.MainScreen -> {
            MainScreen( arguments)
        }
        Screens.SelectionScreen -> {
            SelectionScreen(exampleData.list, arguments)
        }
        Screens.DetailsScreen -> {
            Details(exampleData.filmMore, arguments)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FilmoTheme {

    }
}

fun Context.getActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) {
            return context
        }
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}

