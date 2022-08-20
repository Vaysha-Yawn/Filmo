package com.example.filmo.retrofit.model

data class SearchData(
    var searchType : String,
    var expression : String,
    var results : List<SearchResult>,
    var errorMessage : String,
)

data class SearchResult(
    var id : String,
    var resultType : String?,
    var image : String?,
    var title : String?,
    var description : String?,
)