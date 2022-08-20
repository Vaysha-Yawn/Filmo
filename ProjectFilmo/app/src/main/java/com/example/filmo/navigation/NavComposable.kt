package com.example.filmo.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import com.example.filmo.ui.composableFunctions.Details
import com.example.filmo.ui.composableFunctions.MainScreen
import com.example.filmo.ui.composableFunctions.SelectionScreen


@Composable
fun DrawScreen(nameScreen: Screens, arguments: Bundle) {
    when (nameScreen) {
        Screens.MainScreen -> {
            MainScreen(arguments)
        }
        Screens.SelectionScreen -> {
            SelectionScreen(arguments)
        }
        Screens.DetailsScreen -> {
            Details(arguments)
        }
    }
}