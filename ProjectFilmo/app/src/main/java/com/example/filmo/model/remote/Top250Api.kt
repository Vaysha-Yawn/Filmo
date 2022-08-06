package com.example.filmo.model.remote

import com.example.filmo.model.remote.dataClass.Top250Data
import retrofit2.Response
import retrofit2.http.GET

interface Top250Api {

    @GET("/Top250Movies")
    suspend fun getTop250(): Response<Top250Data>

}