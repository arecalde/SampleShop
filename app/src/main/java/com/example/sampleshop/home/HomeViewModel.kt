package com.example.sampleshop.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    val loading = MutableLiveData(true)
}