package com.example.filmo.navigation

import java.io.Serializable


enum class Screens:Serializable{
    MainScreen,
    SelectionScreen,
    DetailsScreen
}

const val TITLE = "title"
const val ID = "id"
const val SEARCH = "search"
const val PREVSCREEN = "previousScreen"
const val PREVSCREENDATA = "previousScreenData"
