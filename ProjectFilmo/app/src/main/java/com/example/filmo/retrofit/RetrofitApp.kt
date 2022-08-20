package com.example.filmo.retrofit

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApp : Application() {

    lateinit var movieApi: RetrofitMovieApi

    private val baseUrlStart = "https://imdb-api.com/"
    private val baseUrlEnd = "/API/"

    override fun onCreate() {
        super.onCreate()

        val locale = this.resources.configuration.locales[0].language
        movieApi = initRetrofit(locale)
    }

    private fun initRetrofit(locale: String):
            RetrofitMovieApi {
        return Retrofit.Builder().baseUrl(baseUrlStart + locale + baseUrlEnd)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitMovieApi::class.java)
    }

}