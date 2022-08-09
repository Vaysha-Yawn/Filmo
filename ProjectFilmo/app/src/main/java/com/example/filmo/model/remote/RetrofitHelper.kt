package com.example.filmo.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    val baseUrlStart = "https://imdb-api.com/"

    val baseUrlEnd = "/API/"

    fun getInstance(lang:String = "en"): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrlStart+lang+baseUrlEnd)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}
