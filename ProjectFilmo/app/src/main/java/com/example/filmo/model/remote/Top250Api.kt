package com.example.filmo.model.remote

import com.example.filmo.model.remote.dataClass.MostPopularData
import com.example.filmo.model.remote.dataClass.NewMovieData
import com.example.filmo.model.remote.dataClass.Top250Data
import retrofit2.Response
import retrofit2.http.GET

const val myKey = "k_smi0n084"
interface Top250Api {

    @GET("Top250Movies/$myKey")
    suspend fun getTop250(): Response<Top250Data>

    @GET("MostPopularMovies/$myKey")
    suspend fun getMostPopularMovies(): Response<MostPopularData>

    @GET("InTheaters/$myKey")
    suspend fun getInTheaters(): Response<NewMovieData>

    @GET("ComingSoon/$myKey")
    suspend fun getComingSoon(): Response<NewMovieData>
}