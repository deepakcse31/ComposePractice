package com.example.testing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

class MainViewModel :ViewModel() {
    private val upidata=MutableLiveData<String>()

    val _upidata: LiveData<String> get()=upidata
    fun deepak(name : String)
    {
        upidata.value="Deepak"

    }
}