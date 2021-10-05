package com.example.databaseroom.injection

import android.content.Context
import com.example.databaseroom.dao.JenisBukuDao
import com.example.databaseroom.dao.RakBukuDao
import com.example.databaseroom.db.DataBaseBuku
import com.example.databaseroom.repo.RepositoryData
import com.example.databaseroom.repo.RepositoryJenisBuku

object Injection {
    //rak
    //fun agar database menjadi context dan bisa manggil isinya (dari database)
    private fun dataBase(context: Context): DataBaseBuku = DataBaseBuku.getDatabase(context)

    //menyediakanDaoRakBuku
    fun provideDaoRakBuku(context: Context): RakBukuDao = dataBase(context).rakBukuDao()

    //menyediakanRakBukuRepository
    fun provideRakRepository(context: Context): RepositoryData = RepositoryData(
        provideDaoRakBuku(context)
    )

    //jenis
    //menyediakanDaoJenisBuku
    fun provideDaoJenisBuku(context: Context): JenisBukuDao = dataBase(context).jenisBukuDao()

    //menyediakanJenisBukuRepository
    fun provideJenisBukuDao(context: Context): RepositoryJenisBuku = RepositoryJenisBuku(
        provideDaoJenisBuku(context)
    )
}