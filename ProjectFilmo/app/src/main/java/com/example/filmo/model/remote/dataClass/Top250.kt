package com.example.filmo.model.remote.dataClass

data class Top250Data(
    var Items : List<Top250DataDetail>,
    var ErrorMessage : String
)

data class Top250DataDetail(
    var Id : String,
    var Rank : String,
    var Title : String,
    var FullTitle : String,
    var Year : String,
    var Image : String,
    var Crew : String,
    var IMDbRating : String,
    var IMDbRatingCount : String,
)