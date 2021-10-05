package com.example.databaseroom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.databaseroom.entity.RakBukuEntity
import com.example.databaseroom.repo.RepositoryData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EdtRakBukuViewModel(private val rakBukuRepo: RepositoryData) : ViewModel() {

    fun getRakBukuById(id: Int): LiveData<RakBukuEntity> {
        val resultRakbuku = MutableLiveData<RakBukuEntity>()
        viewModelScope.launch(Dispatchers.IO) {
            resultRakbuku.postValue(rakBukuRepo.getRakBukuById(id))
        }
        return resultRakbuku
    }

    fun updateRakBuku(updateRakBukuEntity: RakBukuEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            rakBukuRepo.updateRakBuku(updateRakBukuEntity)
        }
    }
}