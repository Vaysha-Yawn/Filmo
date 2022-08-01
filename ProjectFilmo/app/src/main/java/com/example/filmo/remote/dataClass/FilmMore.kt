package com.example.filmo.remote.dataClass

data class FilmMore(
    val id:String,
    val title:String,
    val year:String,
    val rating:String,
    val poster:String,
    val trailer:String,
    val genres:String,
    val releaseDate:String,
    val description:String,
    val actors:List<Actor>
    )