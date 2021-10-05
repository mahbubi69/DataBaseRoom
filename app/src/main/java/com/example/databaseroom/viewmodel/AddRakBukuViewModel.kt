package com.example.databaseroom.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.databaseroom.entity.RakBukuEntity
import com.example.databaseroom.repo.RepositoryData
import kotlinx.coroutines.launch


class AddRakBukuViewModel(private val rakBukuRepo: RepositoryData) : ViewModel() {

    //view model agar data tetap adi ketika begeser activity/fragment
    fun mengambilRakBuku() = rakBukuRepo.getAllDataRakBuku(viewModelScope)

    fun nambahRakBuku(addRakBukuEntity: RakBukuEntity) {
        viewModelScope.launch { rakBukuRepo.insert(addRakBukuEntity) }
    }

    fun deletRakBuku(deletRakEntity: RakBukuEntity) {
       viewModelScope.launch { rakBukuRepo.deletData(deletRakEntity) }
    }

}

