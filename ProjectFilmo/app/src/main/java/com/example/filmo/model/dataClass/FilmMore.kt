package com.example.filmo.model.dataClass

data class FilmMore(
    val id:String,
    val title:String?,
    val year:String?,
    val rating:String?,
    val poster:String,
    val genres:List<String?>,
    val releaseDate:String?,
    val description:String?,
    val actors:List<Actor>
    )