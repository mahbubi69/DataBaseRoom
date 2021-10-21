package com.example.databaseroom.injection

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.databaseroom.repo.RepositoryJenisBuku
import com.example.databaseroom.viewmodel.AddJenisBukuViewModel


//view model pabrik (mengelola view model)
class ViewModelJenisBukuFactory(
    private val addJenisBukuFactory: RepositoryJenisBuku,
) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var contoh: ViewModelJenisBukuFactory? = null
        fun ambilContoh(context: Context): ViewModelJenisBukuFactory =
            (contoh ?: synchronized(this) {
                contoh ?: ViewModelJenisBukuFactory(
                    Injection.provideJenisBukuDao(context)
                )
            })
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AddJenisBukuViewModel::class.java) -> {
                AddJenisBukuViewModel(addJenisBukuFactory) as T
            }
            modelClass.isAssignableFrom(AddJenisBukuViewModel::class.java) -> {
                AddJenisBukuViewModel(addJenisBukuFactory) as T
            }
            else -> throw Throwable("view model class kosong" + modelClass.name)
        }
    }
}