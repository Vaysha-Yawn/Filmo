package com.example.filmo

import androidx.lifecycle.ViewModel
import com.example.filmo.model.remote.RetrofitHelper
import com.example.filmo.model.remote.Top250Api
import com.example.filmo.model.remote.dataClass.Top250Data
import com.example.filmo.model.remote.dataClass.Top250DataDetail
import kotlinx.coroutines.*

class MainVM:ViewModel() {
    val top250Api = RetrofitHelper.getInstance().create(Top250Api::class.java)

    private val viewModelJob = SupervisorJob()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun getTop250(): List<Top250DataDetail>?{
        var items:List<Top250DataDetail>? = null
        uiScope.launch {
            val result = top250Api.getTop250()
            if (result.isSuccessful){
                items = result.body()?.Items
            }
        }
        return items
    }


}