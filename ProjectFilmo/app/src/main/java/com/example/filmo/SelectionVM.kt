package com.example.filmo

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.filmo.model.dataClass.FilmShort
import com.example.filmo.model.remote.RetrofitHelper
import com.example.filmo.model.remote.Top250Api
import com.example.filmo.ui.exampleData
import kotlinx.coroutines.*

class SelectionVM:ViewModel() {
    val top250Api = RetrofitHelper.getInstance("en").create(Top250Api::class.java)

    private val viewModelJob = SupervisorJob()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    val liveList = mutableStateOf(exampleData.list.toList())

    fun loadTop250(){
        val listResult = mutableListOf<FilmShort>()
        uiScope.launch {
            val result = top250Api.getTop250()

            if (result.isSuccessful){
                val items = result.body()?.items
                if (items!=null){
                    for (item in items){
                        val film = FilmShort(item.id, item.image, item.title, item.imDbRating, item.year)
                        listResult.add(film)
                    }
                }
            }

            liveList.value = listResult
        }
    }

    fun loadComingSoon(){
        val listResult = mutableListOf<FilmShort>()
        uiScope.launch {
            val result = top250Api.getComingSoon()
            if (result.isSuccessful){
                val items = result.body()?.items
                if (items!=null){
                    for (item in items){
                        val film = FilmShort(item.id, item.image, item.title, item.imDbRating, item.year)
                        listResult.add(film)
                    }
                }
            }
            liveList.value = listResult
        }
    }

    fun loadInTheaters(){
        val listResult = mutableListOf<FilmShort>()
        uiScope.launch {
            val result = top250Api.getInTheaters()
            if (result.isSuccessful){
                val items = result.body()?.items
                if (items!=null){
                    for (item in items){
                        val film = FilmShort(item.id, item.image, item.title, item.imDbRating, item.year)
                        listResult.add(film)
                    }
                }
            }
            liveList.value = listResult
        }
    }

    fun loadMostPopularMovies(){
        val listResult = mutableListOf<FilmShort>()
        uiScope.launch {
            val result = top250Api.getMostPopularMovies()
            if (result.isSuccessful){
                val items = result.body()?.items
                if (items!=null){
                    for (item in items){
                        val film = FilmShort(item.id, item.image, item.title, item.imDbRating, item.year)
                        listResult.add(film)
                    }
                }
            }
            liveList.value = listResult
        }
    }

}