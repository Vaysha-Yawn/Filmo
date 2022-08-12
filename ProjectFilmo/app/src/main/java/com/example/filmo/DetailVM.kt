package com.example.filmo

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.filmo.model.dataClass.FilmMore
import com.example.filmo.model.remote.RetrofitHelper
import com.example.filmo.model.remote.RetrofitMovieApi
import com.example.filmo.ui.exampleData
import kotlinx.coroutines.*

class DetailVM : ViewModel() {

    lateinit var top250Api: RetrofitMovieApi

    private val viewModelJob = SupervisorJob()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    val liveFilm = mutableStateOf(exampleData.filmMore)

    fun loadFilm(id: String) {
        uiScope.launch {
            val result = top250Api.getFilmDetail(id)
            if (result.isSuccessful) {
                val titleData = result.body()
                if (titleData != null) {
                    val genres = mutableListOf<String?>()
                    if (titleData.genreList!=null){
                        for (gen in titleData.genreList!!) {
                            genres.add(gen.value)
                        }
                    }
                    var plot = titleData.plotLocal
                    if(plot == ""){
                        plot = titleData.plot
                    }
                    liveFilm.value = FilmMore(
                        id = titleData.id,
                        title = titleData.title,
                        year = titleData.year,
                        rating = titleData.imDbRating,
                        poster = titleData.image,
                        genres = genres,
                        releaseDate = titleData.releaseDate,
                        description = plot,
                        actors = titleData.actorList
                    )
                }
            }
        }
    }

}