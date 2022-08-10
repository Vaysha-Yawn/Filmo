package com.example.filmo.model.remote

import com.example.filmo.model.remote.dataClass.MostPopularData
import com.example.filmo.model.remote.dataClass.NewMovieData
import com.example.filmo.model.remote.dataClass.TitleData
import com.example.filmo.model.remote.dataClass.Top250Data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

//const val myKey = "k_smi0n084"
//const val myKey = "k_lx2w9d56"
const val myKey = "k_mi7e7614"

interface Top250Api {

    @GET("Top250Movies/$myKey")
    suspend fun getTop250(): Response<Top250Data>

    @GET("MostPopularMovies/$myKey")
    suspend fun getMostPopularMovies(): Response<MostPopularData>

    @GET("InTheaters/$myKey")
    suspend fun getInTheaters(): Response<NewMovieData>

    @GET("ComingSoon/$myKey")
    suspend fun getComingSoon(): Response<NewMovieData>

    @GET("Title/$myKey/{id}/FullActor,Trailer,")
    suspend fun getFilmDetail(@Path("id")id:String): Response<TitleData>
}