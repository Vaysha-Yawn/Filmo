package com.example.filmo.retrofit.model

import com.example.filmo.model.dataClass.Actor

data class NewMovieData(
    var items : List<NewMovieDataDetail>,
    var errorMessage : String
)

data class NewMovieDataDetail(
    var id : String,
    var title : String?,
    var fullTitle : String?,
    var year : String?,
    var releaseState : String?,
    var image : String?,
    var runtimeMins : String?,
    var runtimeStr : String?,
    var plot : String?,
    var ContentRating : String?,
    var imDbRating : String?,
    var IMDbRatingCount : String?,
    var MetacriticRating : String?,
    var genres : String?,
    var genreList : List<KeyValueItem?>,
    var directors : String?,
    var directorList : List<Actor>?,
    var stars : String?,
    var starList : List<Actor>?,
)

data class KeyValueItem(
    var key : String?,
    var value : String?,
)
