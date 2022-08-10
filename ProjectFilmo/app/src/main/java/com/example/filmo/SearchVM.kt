package com.example.filmo

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.filmo.model.dataClass.FilmMore
import com.example.filmo.model.dataClass.FilmShort
import com.example.filmo.model.remote.RetrofitHelper
import com.example.filmo.model.remote.RetrofitMovieApi
import com.example.filmo.ui.exampleData
import kotlinx.coroutines.*

class SearchVM : ViewModel() {
    val top250Api = RetrofitHelper.getInstance("en").create(RetrofitMovieApi::class.java)

    private val viewModelJob = SupervisorJob()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    val liveFilm = mutableStateOf(exampleData.list.toList())

    fun loadSearch(search: String) {
        uiScope.launch {
            val result = top250Api.getSearchData(search)
            if (result.isSuccessful) {
                val searchData = result.body()
                if (searchData != null) {
                    val list = mutableListOf<FilmShort>()
                    for (res in searchData.results) {
                        val filmShort = FilmShort(
                            id = res.id,
                            poster = res.image,
                            title = res.title,
                            rating = "",
                            year = ""
                        )
                        list.add(filmShort)
                    }
                    liveFilm.value = list
                }
            }
        }
    }

}