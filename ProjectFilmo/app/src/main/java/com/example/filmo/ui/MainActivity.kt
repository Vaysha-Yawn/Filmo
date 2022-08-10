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
import com.example.filmo.MainVM
import com.example.filmo.SelectionVM
import com.example.filmo.model.Screens
import com.example.filmo.model.testData.TestData
import com.example.filmo.ui.composableFunctions.*
import com.example.filmo.ui.theme.FilmoTheme
import java.lang.IllegalStateException


val exampleData = TestData()

class MainActivity : ComponentActivity() {

    val mainVM:MainVM by viewModels()
    val selectionVM:SelectionVM by viewModels()

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

