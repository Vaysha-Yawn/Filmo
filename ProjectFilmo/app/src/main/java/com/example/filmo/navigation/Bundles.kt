package com.example.filmo.navigation

import android.os.Bundle
import androidx.core.os.bundleOf

fun getBundleForDetailsScreen(
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

fun getBundleForMainScreen(search: String): Bundle {
    return bundleOf(Pair(SEARCH, search))
}

fun getBundleForSelectionScreen(title:String):Bundle{
    return bundleOf(Pair(TITLE, title))
}