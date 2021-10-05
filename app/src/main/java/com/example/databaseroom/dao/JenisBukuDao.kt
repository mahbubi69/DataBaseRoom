package com.example.databaseroom.dao


import androidx.room.*
import com.example.databaseroom.entity.JenisBukuEntity


@Dao
interface JenisBukuDao {
    @Query("SELECT * FROM tbl_jenis_buku ORDER BY id_jenis_buku ASC")
    fun getDataJenisBuku(): List<JenisBukuEntity>

    @Query("SELECT * FROM tbl_jenis_buku WHERE id_jenis_buku =:idJenis ")
    suspend fun getIdJenisBuku(idJenis: Int): JenisBukuEntity

    //menambahkan data
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertJenisDataBuku(namaJenis: JenisBukuEntity)

    @Delete
    suspend fun deletJenisBuku(namaJenis: JenisBukuEntity)

    @Update
    suspend fun updateJenisBuku(namaJenis: JenisBukuEntity)

}