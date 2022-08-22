package com.udacity.shoestore

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ViewModelClass : ViewModel() {

    val list:LiveData<MutableList<Shoe>>
        get() =privateList
    private val privateList= MutableLiveData<MutableList<Shoe>>()
    //////////////////////////



    init {
        // for test
        privateList.value= mutableListOf()
    //    addItem(Shoe("aaaa", 15.0,"aaaa","aaaa",))
    }
    fun addItem(shoe: Shoe){
        privateList.value?.let {
            it.add(shoe)
        }
    }

}