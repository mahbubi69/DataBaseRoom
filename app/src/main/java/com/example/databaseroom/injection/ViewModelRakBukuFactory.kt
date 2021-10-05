package com.example.databaseroom.injection

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.databaseroom.repo.RepositoryData
import com.example.databaseroom.viewmodel.AddRakBukuViewModel
import com.example.databaseroom.viewmodel.EdtRakBukuViewModel

//viewmodel pabrik
class ViewModelRakBukuFactory(
    private val rakBukuRepository: RepositoryData
) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelRakBukuFactory? = null
        fun getInstance(context: Context): ViewModelRakBukuFactory =
            (instance ?: synchronized(this) {
                instance ?: ViewModelRakBukuFactory(
                    Injection.provideRakRepository(context)
                )
            })
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AddRakBukuViewModel::class.java) -> {
                AddRakBukuViewModel(rakBukuRepository) as T
            }
            modelClass.isAssignableFrom(EdtRakBukuViewModel::class.java) -> {
                EdtRakBukuViewModel(rakBukuRepository) as T
            }
            else -> throw Throwable("ViewModel class kosong" + modelClass.name)
        }
    }
}