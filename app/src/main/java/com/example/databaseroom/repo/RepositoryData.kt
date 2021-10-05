package com.example.databaseroom.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.databaseroom.dao.JenisBukuDao
import com.example.databaseroom.dao.RakBukuDao
import com.example.databaseroom.entity.JenisBukuEntity
import com.example.databaseroom.entity.RakBukuEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//pengolahan data
class RepositoryData(
    private val rakBukuDao: RakBukuDao
) {

    suspend fun insert(rakBukuEntity: RakBukuEntity) {
        rakBukuDao.insertData(rakBukuEntity)
    }

    suspend fun deletData(deletRakBuku: RakBukuEntity) {
        rakBukuDao.deletRakBuku(deletRakBuku)
    }

    suspend fun updateRakBuku(updateRakBukuEntity: RakBukuEntity) {
        rakBukuDao.updateRakBuku(updateRakBukuEntity)
    }

    suspend fun getRakBukuById(id: Int): RakBukuEntity {
        return rakBukuDao.getRakBukuById(id)
    }

    //coroutin
    //hasil pengolahan data (query)
    fun getAllDataRakBuku(scope: CoroutineScope): LiveData<List<RakBukuEntity>> {
        //hasil
        val result = MutableLiveData<List<RakBukuEntity>>()
        scope.launch {
            val rakBuku = rakBukuDao.getRakData()
            result.postValue(rakBuku)
        }
        return result
    }

}