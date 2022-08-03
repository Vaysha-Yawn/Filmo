package com.example.filmo.testData

import com.example.filmo.remote.dataClass.Actor
import com.example.filmo.remote.dataClass.FilmMore
import com.example.filmo.remote.dataClass.FilmShort

class TestData {

    val filmShort = FilmShort(
        id = "tt0111161",
        title = "The Shawshank Redemption",
        year = "1994",
        poster = "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_Ratio0.6716_AL_.jpg",
        rating = "9.3"
    )

    val filmMore = FilmMore(
        id = "tt0111161",
        title = "The Shawshank Redemption",
        year = "1994",
        rating = "9.3",
        poster = "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_Ratio0.6837_AL_.jpg",
        trailer = "https://www.imdb.com/video/imdb/vi3877612057/imdb/embed",
        genres = listOf("Drama"),
        releaseDate = "1994-10-14",
        description = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
        actors = listOf(
            Actor(
                id = "nm0000209",
                image = "https://imdb-api.com/images/original/MV5BMTI1OTYxNzAxOF5BMl5BanBnXkFtZTYwNTE5ODI4._V1_Ratio0.7727_AL_.jpg",
                name = "Tim Robbins",
                asCharacter = "Andy Dufresne"
            ),
            Actor(
                id = "nm0000151",
                image = "https://imdb-api.com/images/original/MV5BMTc0MDMyMzI2OF5BMl5BanBnXkFtZTcwMzM2OTk1MQ@@._V1_Ratio0.7273_AL_.jpg",
                name = "Morgan Freeman",
                asCharacter = "Ellis Boyd 'Red' Redding"
            ),
            Actor(
                id = "nm0348409",
                image = "https://imdb-api.com/images/original/MV5BMjUyZDQ0NjktZmM5ZS00NzcxLTliMWYtNWUxNDcyMmExZjU0XkEyXkFqcGdeQXVyMTE1MjA4NzM@._V1_Ratio0.7727_AL_.jpg",
                name = "Bob Gunton",
                asCharacter = "Warden Norton"
            ),
            Actor(
                id = "tt0111161",
                image = "https://imdb-api.com/images/original/MV5BMTA1NjU3NDg1MTheQTJeQWpwZ15BbWU2MDI4OTcxMw@@._V1_Ratio0.8182_AL_.jpg",
                name = "William Sadler",
                asCharacter = "Heywood"
            )
        )
    )

    val list = mutableListOf<FilmShort>()
    val map = mutableMapOf<String, MutableList<FilmShort>>()
    val title = "Топ 250 фильмов"

    init{
        for (i in 1..20){
            list.add(filmShort)
        }

        for (i in 1..10){
            map["Топ 250 фильмов $i"] = list
        }
    }

}
