package com.example.databaseroom.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_rakbuku")
data class RakBukuEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "jenis_buku")
    val jenisBuku: String,
    @ColumnInfo(name = "kata_kata")
    val kata: String,
    @ColumnInfo(name = "tanggal")
    val tanggal: String
)