package com.example.databaseroom.handler

import com.example.databaseroom.entity.JenisBukuEntity
import com.example.databaseroom.entity.RakBukuEntity

interface OnClikHandler {
    //rak
    fun onclikRakBuku(rakBuku: RakBukuEntity)
    fun onDelet(rakBuku: RakBukuEntity)


}