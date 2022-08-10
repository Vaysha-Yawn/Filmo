package com.example.filmo

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.filmo.model.dataClass.Actor
import com.example.filmo.model.dataClass.FilmMore
import com.example.filmo.model.dataClass.FilmShort
import com.example.filmo.model.remote.RetrofitHelper
import com.example.filmo.model.remote.Top250Api
import com.example.filmo.ui.exampleData
import kotlinx.coroutines.*

class DetailVM : ViewModel() {
    val top250Api = RetrofitHelper.getInstance("en").create(Top250Api::class.java)

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
                    liveFilm.value = FilmMore(
                        id = titleData.id,
                        title = titleData.title,
                        year = titleData.year,
                        rating = titleData.imDbRating,
                        poster = titleData.image,
                        genres = genres,
                        releaseDate = titleData.releaseDate,
                        description = titleData.plot,
                        actors = titleData.actorList
                    )
                }
            }
        }
    }

}