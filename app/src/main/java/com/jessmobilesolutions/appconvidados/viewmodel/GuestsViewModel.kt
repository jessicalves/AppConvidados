package com.jessmobilesolutions.appconvidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jessmobilesolutions.appconvidados.model.GuestModel
import com.jessmobilesolutions.appconvidados.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val  repository = GuestRepository.getInstance(application.applicationContext)
    private val listAllGuests = MutableLiveData<List<GuestModel>>().apply {}
    val guests: LiveData<List<GuestModel>> = listAllGuests

    fun getAll(){
        listAllGuests.value =  repository.getAll()
    }

    fun getAbsent(){
        listAllGuests.value =  repository.getAbsent()
    }

    fun getPresent(){
        listAllGuests.value =  repository.getPresent()
    }

    fun delete(id: Int){
        repository.delete(id)
    }
}