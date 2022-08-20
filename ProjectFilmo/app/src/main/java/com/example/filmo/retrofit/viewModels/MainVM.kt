package com.example.filmo.retrofit.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.filmo.model.dataClass.FilmShort
import com.example.filmo.retrofit.RetrofitMovieApi
import com.example.filmo.ui.exampleData
import kotlinx.coroutines.*

class MainVM : ViewModel() {

    private val viewModelJob = SupervisorJob()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun loadAll(app: RetrofitMovieApi, key: String): Boolean {
        var res = true
        val resultTop250 = loadTop250(app, key)
        val resultComingSoon = loadComingSoon(app, key)
        val resultInTheaters = loadInTheaters(app, key)
        val resultMostPopularMovies = loadMostPopularMovies(app, key)

        if (resultTop250 == false || resultComingSoon == false || resultInTheaters == false || resultMostPopularMovies == false) {
            res = false
        }
        Log.e("ddddddd","$resultTop250 $resultComingSoon $resultInTheaters $resultMostPopularMovies")
        return res
    }

    val liveTop250 = mutableStateOf(exampleData.list.toList())
    val liveMostPopularMovies = mutableStateOf(exampleData.list.toList())
    val liveInTheaters = mutableStateOf(exampleData.list.toList())
    val liveComingSoon = mutableStateOf(exampleData.list.toList())

    fun loadTop250(movieApi: RetrofitMovieApi, key: String): Boolean {
        Log.e("ddddddd","loadTop250")
        val listResult = mutableListOf<FilmShort>()
        val load = uiScope.async {
            val forReturn = withContext(Dispatchers.Default) {
                val result = movieApi.getTop250(key)
                if (result.isSuccessful) {
                    val items = result.body()?.items
                    if (items != null && items.isNotEmpty()) {
                        for (item in items.subList(0, 5)) {
                            val film =
                                FilmShort(
                                    item.id,
                                    item.image,
                                    item.title,
                                    item.imDbRating,
                                    item.year
                                )
                            listResult.add(film)
                        }
                        Log.e("ddddddd","Hello")
                        return@withContext true
                    } else {
                        Log.e("ddddddd","empty")
                        return@withContext false
                    }
                } else {
                    Log.e("ddddddd","fail")
                    return@withContext false
                }
            }
            liveTop250.value = listResult
            return@async forReturn
        }

        return load.toString().toBoolean()
    }

    private fun loadComingSoon(movieApi: RetrofitMovieApi, key: String): Boolean {

        val listResult = mutableListOf<FilmShort>()
        val load = uiScope.async {
            val forReturn = withContext(Dispatchers.Default) {
                val result = movieApi.getComingSoon(key)
                if (result.isSuccessful) {
                    val items = result.body()?.items
                    if (items != null && items.isNotEmpty()) {
                        for (item in items.subList(0, 5)) {
                            val film =
                                FilmShort(
                                    item.id,
                                    item.image,
                                    item.title,
                                    item.imDbRating,
                                    item.year
                                )
                            listResult.add(film)
                        }
                        return@withContext true
                    } else {
                        return@withContext false
                    }
                } else {
                    return@withContext false
                }
            }
            liveComingSoon.value = listResult
            return@async forReturn
        }
        return load.toString().toBoolean()
    }


    private fun loadInTheaters(movieApi: RetrofitMovieApi, key: String): Boolean {
        val listResult = mutableListOf<FilmShort>()

        val load = uiScope.async {
            val forReturn = withContext(Dispatchers.Default) {
                val result = movieApi.getInTheaters(key)
                if (result.isSuccessful) {
                    val items = result.body()?.items
                    if (items != null && items.isNotEmpty()) {
                        for (item in items.subList(0, 5)) {
                            val film =
                                FilmShort(
                                    item.id,
                                    item.image,
                                    item.title,
                                    item.imDbRating,
                                    item.year
                                )
                            listResult.add(film)
                        }
                        return@withContext true
                    } else {
                        return@withContext false
                    }
                } else {
                    return@withContext false
                }
            }
            liveInTheaters.value = listResult
            return@async forReturn
        }
        return load.toString().toBoolean()
    }


    private fun loadMostPopularMovies(movieApi: RetrofitMovieApi, key: String): Boolean {
        val listResult = mutableListOf<FilmShort>()

        val load = uiScope.async {
            val forReturn = withContext(Dispatchers.Default) {
                val result = movieApi.getMostPopularMovies(key)
                if (result.isSuccessful) {
                    val items = result.body()?.items
                    if (items != null && items.isNotEmpty()) {
                        for (item in items.subList(0, 5)) {
                            val film =
                                FilmShort(
                                    item.id,
                                    item.image,
                                    item.title,
                                    item.imDbRating,
                                    item.year
                                )
                            listResult.add(film)
                        }
                        return@withContext true
                    } else {
                        return@withContext false
                    }
                } else {
                    return@withContext false
                }
            }
            liveMostPopularMovies.value = listResult
            return@async forReturn
        }
        return load.toString().toBoolean()
    }


}