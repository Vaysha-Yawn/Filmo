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

class SearchVM : ViewModel() {

    private val viewModelJob = SupervisorJob()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    val liveFilm = mutableStateOf(exampleData.list.toList())

    fun loadSearch(movieApi: RetrofitMovieApi, search: String, key: String) {
        uiScope.launch {
            val result = movieApi.getSearchData(search, key)
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