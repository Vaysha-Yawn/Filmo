package com.example.filmo.retrofit

import com.example.filmo.retrofit.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

//const val myKey = "k_smi0n084"
//const val myKey = "k_lx2w9d56"
//const val myKey = "k_mi7e7614"
//const val myKey = "k_lb8xezv4"

interface RetrofitMovieApi {

    @GET("Top250Movies/{myKey}")
    suspend fun getTop250(@Path("myKey")myKey:String): Response<Top250Data>

    @GET("MostPopularMovies/{myKey}")
    suspend fun getMostPopularMovies(@Path("myKey")myKey:String): Response<MostPopularData>

    @GET("InTheaters/{myKey}")
    suspend fun getInTheaters(@Path("myKey")myKey:String): Response<NewMovieData>

    @GET("ComingSoon/{myKey}")
    suspend fun getComingSoon(@Path("myKey")myKey:String): Response<NewMovieData>

    @GET("Title/{myKey}/{id}/FullActor,Trailer,")
    suspend fun getFilmDetail(@Path("id")id:String, @Path("myKey")myKey:String): Response<TitleData>

    @GET("SearchMovie/{myKey}/{search}")
    suspend fun getSearchData(@Path("search")search:String, @Path("myKey")myKey:String): Response<SearchData>
}