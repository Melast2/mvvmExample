package com.example.mvvm.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.model.NicePlace

class MainViewModel: ViewModel() {
    var lst = MutableLiveData<ArrayList<NicePlace>>()
    var newlist = arrayListOf<NicePlace>()

    fun add(blog: NicePlace){
        newlist.add(blog)
        lst.value=newlist
    }

    fun remove(blog: NicePlace){
        newlist.remove(blog)
        lst.value=newlist
    }

}