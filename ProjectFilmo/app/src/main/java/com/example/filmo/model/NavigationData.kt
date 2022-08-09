package com.example.filmo.model

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

const val TOP250 = "Top250Movies"
const val MOSTPOPULAR = "MostPopularMovies"
const val INTHEATERS = "InTheaters"
const val COMINGSOON = "ComingSoon"