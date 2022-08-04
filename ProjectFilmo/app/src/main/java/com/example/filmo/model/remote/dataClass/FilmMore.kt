package com.example.filmo.model.remote.dataClass

data class FilmMore(
    val id:String,
    val title:String,
    val year:String,
    val rating:String,
    val poster:String,
    val trailer:String,
    val genres:List<String>,
    val releaseDate:String,
    val description:String,
    val actors:List<Actor>
    )