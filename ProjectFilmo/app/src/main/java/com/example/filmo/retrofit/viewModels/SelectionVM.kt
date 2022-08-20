package com.example.filmo.retrofit.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.filmo.model.dataClass.FilmShort
import com.example.filmo.retrofit.RetrofitMovieApi
import com.example.filmo.ui.exampleData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class SelectionVM : ViewModel() {

    private val viewModelJob = SupervisorJob()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    val liveList = mutableStateOf(exampleData.list.toList())

    fun loadTop250(movieApi: RetrofitMovieApi, key: String) {
        val listResult = mutableListOf<FilmShort>()
        uiScope.launch {
            val result = movieApi.getTop250(key)

            if (result.isSuccessful) {
                val items = result.body()?.items
                if (items != null) {
                    for (item in items) {
                        val film =
                            FilmShort(item.id, item.image, item.title, item.imDbRating, item.year)
                        listResult.add(film)
                    }
                }
            }

            liveList.value = listResult
        }
    }

    fun loadComingSoon(movieApi: RetrofitMovieApi, key: String) {
        val listResult = mutableListOf<FilmShort>()
        uiScope.launch {
            val result = movieApi.getComingSoon(key)
            if (result.isSuccessful) {
                val items = result.body()?.items
                if (items != null) {
                    for (item in items) {
                        val film =
                            FilmShort(item.id, item.image, item.title, item.imDbRating, item.year)
                        listResult.add(film)
                    }
                }
            }
            liveList.value = listResult
        }
    }

    fun loadInTheaters(movieApi: RetrofitMovieApi, key: String) {
        val listResult = mutableListOf<FilmShort>()
        uiScope.launch {
            val result = movieApi.getInTheaters(key)
            if (result.isSuccessful) {
                val items = result.body()?.items
                if (items != null) {
                    for (item in items) {
                        val film =
                            FilmShort(item.id, item.image, item.title, item.imDbRating, item.year)
                        listResult.add(film)
                    }
                }
            }
            liveList.value = listResult
        }
    }

    fun loadMostPopularMovies(movieApi: RetrofitMovieApi, key: String) {
        val listResult = mutableListOf<FilmShort>()
        uiScope.launch {
            val result = movieApi.getMostPopularMovies(key)
            if (result.isSuccessful) {
                val items = result.body()?.items
                if (items != null) {
                    for (item in items) {
                        val film =
                            FilmShort(item.id, item.image, item.title, item.imDbRating, item.year)
                        listResult.add(film)
                    }
                }
            }
            liveList.value = listResult
        }
    }

}