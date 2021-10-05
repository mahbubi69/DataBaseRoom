package com.example.databaseroom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.databaseroom.entity.JenisBukuEntity
import com.example.databaseroom.repo.RepositoryJenisBuku
import kotlinx.coroutines.launch

class AddJenisBukuViewModel(
    private val jenisBukuRepo: RepositoryJenisBuku
) : ViewModel() {
    //view model agar data tetap adi ketika begeser activity/fragment
    fun getJenisBuku() = jenisBukuRepo.getAllJenisBuku(viewModelScope)

    //menambahkan jenis buku
    //karena memakai corouti (viewModelScope)
    fun addJenisbuku(addJenisBuku: JenisBukuEntity) {
        viewModelScope.launch { jenisBukuRepo.insertJenisBuku(addJenisBuku) }
    }

    //delet jenis buku
    fun deletJenisBuku(deletJenisBuku: JenisBukuEntity) = viewModelScope.launch {
        jenisBukuRepo.deletJenisBuku(deletJenisBuku)
    }
}