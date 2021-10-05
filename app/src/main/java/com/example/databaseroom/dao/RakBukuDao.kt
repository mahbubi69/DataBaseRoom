package com.example.databaseroom.dao

import androidx.room.*
import com.example.databaseroom.entity.RakBukuEntity


//perintah
@Dao
interface RakBukuDao {

    @Query("SELECT * FROM tbl_rakbuku ORDER BY id ASC")
    suspend fun getRakData(): List<RakBukuEntity>

    @Query("SELECT * FROM tbl_rakbuku WHERE id=:id")
    suspend fun getRakBukuById(id: Int): RakBukuEntity

    //menambahkan database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(namaRakBuku: RakBukuEntity)

    //update database
    @Update
    suspend fun updateRakBuku(updateRakBuku: RakBukuEntity)

    //hapus database
    @Delete
    suspend fun deletRakBuku(deletRakBuku: RakBukuEntity)
}