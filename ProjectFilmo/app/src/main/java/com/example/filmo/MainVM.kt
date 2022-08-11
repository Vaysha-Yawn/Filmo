package com.example.filmo

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.filmo.model.dataClass.FilmShort
import com.example.filmo.model.remote.RetrofitHelper
import com.example.filmo.model.remote.RetrofitMovieApi
import com.example.filmo.ui.exampleData
import kotlinx.coroutines.*

class MainVM:ViewModel() {
    lateinit var top250Api: RetrofitMovieApi

    private val viewModelJob = SupervisorJob()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    val liveTop250 = mutableStateOf(exampleData.list.toList())
    val liveMostPopularMovies = mutableStateOf(exampleData.list.toList())
    val liveInTheaters = mutableStateOf(exampleData.list.toList())
    val liveComingSoon = mutableStateOf(exampleData.list.toList())

    fun loadTop250(){
        val listResult = mutableListOf<FilmShort>()
        uiScope.launch {
            val result = top250Api.getTop250()

            if (result.isSuccessful){
                val items = result.body()?.items
                if (items!=null){
                    for (item in items.subList(0, 5)){
                        val film = FilmShort(item.id, item.image, item.title, item.imDbRating, item.year)
                        listResult.add(film)
                    }
                }
            }

        liveTop250.value = listResult
        }
    }

    fun loadComingSoon(){
        val listResult = mutableListOf<FilmShort>()
        uiScope.launch {
            val result = top250Api.getComingSoon()
            if (result.isSuccessful){
                val items = result.body()?.items
                if (items!=null){
                    for (item in items.subList(0, 5)){
                        val film = FilmShort(item.id, item.image, item.title, item.imDbRating, item.year)
                        listResult.add(film)
                    }
                }
            }
            liveComingSoon.value = listResult
        }
    }

    fun loadInTheaters(){
        val listResult = mutableListOf<FilmShort>()
        uiScope.launch {
            val result = top250Api.getInTheaters()
            if (result.isSuccessful){
                val items = result.body()?.items
                if (items!=null){
                    for (item in items.subList(0, 5)){
                        val film = FilmShort(item.id, item.image, item.title, item.imDbRating, item.year)
                        listResult.add(film)
                    }
                }
            }
            liveInTheaters.value = listResult
        }
    }

    fun loadMostPopularMovies(){
        val listResult = mutableListOf<FilmShort>()
        uiScope.launch {
            val result = top250Api.getMostPopularMovies()
            if (result.isSuccessful){
                val items = result.body()?.items
                if (items!=null){
                    for (item in items.subList(0, 5)){
                        val film = FilmShort(item.id, item.image, item.title, item.imDbRating, item.year)
                        listResult.add(film)
                    }
                }
            }
            liveMostPopularMovies.value = listResult
        }
    }

}