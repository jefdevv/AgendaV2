package br.com.jef.agendav2.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Database
import androidx.room.Room
import br.com.jef.agendav2.data.ItemDataBase

class ItemViewModelFactory(private val applicationContext: Context) : ViewModelProvider.Factory {
    private fun createDatabase(): ItemDataBase {
        return Room.databaseBuilder(
            applicationContext,
            ItemDataBase::class.java,
            "item_database.db"
        ).build()
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ItemViewModel(database = createDatabase()) as T
    }
}