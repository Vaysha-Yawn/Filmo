package com.example.filmo.ui

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.bundleOf
import com.example.filmo.DetailVM
import com.example.filmo.MainVM
import com.example.filmo.R
import com.example.filmo.SearchVM
import com.example.filmo.SelectionVM
import com.example.filmo.model.Screens
import com.example.filmo.model.remote.RetrofitHelper
import com.example.filmo.model.remote.RetrofitMovieApi
import com.example.filmo.model.testData.TestData
import com.example.filmo.ui.composableFunctions.*
import com.example.filmo.ui.theme.FilmoTheme
import java.lang.IllegalStateException


val exampleData = TestData()

class MainActivity : ComponentActivity() {

    val mainVM:MainVM by viewModels()
    val selectionVM:SelectionVM by viewModels()
    val detailVM:DetailVM by viewModels()
    val searchVM:SearchVM by viewModels()

    lateinit var nav: MutableState<Screens>

    var arguments = bundleOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val locale = this.resources.configuration.locales[0].language
        val retrofit = initRetrofit(locale)
        mainVM.top250Api = retrofit
        selectionVM.top250Api = retrofit
        detailVM.top250Api = retrofit
        searchVM.top250Api = retrofit

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

    fun initRetrofit(locale:String):
            RetrofitMovieApi{
        return RetrofitHelper.getInstance(locale).create(RetrofitMovieApi::class.java)
    }

}

@Composable
fun DrawScreen(nameScreen: Screens, arguments: Bundle) {
    when (nameScreen) {
        Screens.MainScreen -> {
            MainScreen( arguments)
        }
        Screens.SelectionScreen -> {
            SelectionScreen(arguments)
        }
        Screens.DetailsScreen -> {
            Details(arguments)
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

