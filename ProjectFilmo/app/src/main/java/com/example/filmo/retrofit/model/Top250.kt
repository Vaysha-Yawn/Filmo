package com.example.filmo.retrofit.model

data class Top250Data(
    var items : List<Top250DataDetail>,
    var errorMessage : String
)

data class Top250DataDetail(
    var id : String,
    var rank : String?,
    var title : String?,
    var fullTitle : String?,
    var year : String?,
    var image : String?,
    var crew : String?,
    var imDbRating : String?,
    var imDbRatingCount : String?,
)