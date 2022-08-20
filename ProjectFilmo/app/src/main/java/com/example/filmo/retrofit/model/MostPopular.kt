package com.example.filmo.retrofit.model

data class MostPopularData(
    var items : List<MostPopularDataDetail>,
    var errorMessage : String
)

data class MostPopularDataDetail(
    var id : String,
    var rank : String?,
    var rankUpDown : String?,
    var title : String?,
    var fullTitle : String?,
    var year : String?,
    var image : String?,
    var crew : String?,
    var imDbRating : String?,
    var imDbRatingCount : String?,
)