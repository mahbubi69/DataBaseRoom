package com.example.databaseroom.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.databaseroom.dao.JenisBukuDao
import com.example.databaseroom.entity.JenisBukuEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//pemgolahan data
class RepositoryJenisBuku(
    private val jenisBukuDao: JenisBukuDao
) {
    //add
    suspend fun insertJenisBuku(jenisBukuEntity: JenisBukuEntity) {
        jenisBukuDao.insertJenisDataBuku(jenisBukuEntity)
    }

    //mengambil id dari jenis buku
    suspend fun getJenisBukuById(idJenisBuku: Int): JenisBukuEntity {
        return jenisBukuDao.getIdJenisBuku(idJenisBuku)
    }

    //hapus
    suspend fun deletJenisBuku(deletjenisBukuEntity: JenisBukuEntity) {
        jenisBukuDao.deletJenisBuku(deletjenisBukuEntity)
    }

    //update
    suspend fun updateJenisBuku(updateJenisBuku: JenisBukuEntity) {
        jenisBukuDao.updateJenisBuku(updateJenisBuku)
    }

    //coroutin
    //hasl dari pengolahan (query)
     fun getAllJenisBuku(scope: CoroutineScope): LiveData<List<JenisBukuEntity>> {
        //hail dari jenis buku
        val hasil = MutableLiveData<List<JenisBukuEntity>>()
        scope.launch {
            val jenisBuku = jenisBukuDao.getDataJenisBuku()
            hasil.postValue(jenisBuku)
        }
        return hasil
    }
}