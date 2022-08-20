package com.example.filmo.retrofit.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.filmo.model.dataClass.FilmMore
import com.example.filmo.retrofit.RetrofitMovieApi
import com.example.filmo.ui.exampleData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class DetailVM : ViewModel() {

    private val viewModelJob = SupervisorJob()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    val liveFilm = mutableStateOf(exampleData.filmMore)

    fun loadFilm(movieApi: RetrofitMovieApi, id: String, key: String) {
        uiScope.launch {
            val result = movieApi.getFilmDetail(id, key)
            if (result.isSuccessful) {
                val titleData = result.body()
                if (titleData != null) {
                    val genres = mutableListOf<String?>()
                    if (titleData.genreList != null) {
                        for (gen in titleData.genreList!!) {
                            genres.add(gen.value)
                        }
                    }
                    var plot = titleData.plotLocal
                    if (plot == "") {
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