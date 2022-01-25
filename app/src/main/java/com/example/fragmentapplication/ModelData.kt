package com.example.fragmentapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class ModelData : ViewModel() {
    val putId: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
}