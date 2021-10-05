package com.example.databaseroom.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.databaseroom.dao.JenisBukuDao
import com.example.databaseroom.dao.RakBukuDao
import com.example.databaseroom.entity.JenisBukuEntity
import com.example.databaseroom.entity.RakBukuEntity


//penggabungan dari colom jadi data base
@Database(
    entities = [
        RakBukuEntity::class,
        JenisBukuEntity::class
    ], version = 1,
    exportSchema = true
)
abstract class DataBaseBuku : RoomDatabase() {
    abstract fun rakBukuDao(): RakBukuDao
    abstract fun jenisBukuDao(): JenisBukuDao

    companion object {
        @Volatile
        private var INSTANCE: DataBaseBuku? = null

        //fun mengambildatabase
        fun getDatabase(context: Context): DataBaseBuku {
            if (INSTANCE == null) {
                synchronized(DataBaseBuku::class.java) {
                    INSTANCE =
                        Room.databaseBuilder(
                            context.applicationContext, DataBaseBuku::class.java,
                            "database_buku"
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE as DataBaseBuku
        }
    }
}