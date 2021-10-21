package com.example.databaseroom.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_jenis_buku")
data class JenisBukuEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_jenis_buku")
    val idJenisBuku: Int = 0,

    @ColumnInfo(name = "judul_buku")
    val judul_buku: String,

    @ColumnInfo(name = "halaman")
    val halaman: String,

    @ColumnInfo(name = "jenis_buku")
    val jenisBuku: String,

    @ColumnInfo(name = "penerbit")
    val penerbit: String,

    @ColumnInfo(name = "tahun_terbit")
    val tahunTerbit: String
)
