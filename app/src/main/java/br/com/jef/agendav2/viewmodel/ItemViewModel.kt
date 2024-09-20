package br.com.jef.agendav2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Database
import br.com.jef.agendav2.data.ItemDataBase
import br.com.jef.agendav2.data.ItemEntity
import br.com.jef.agendav2.data.toModel
import br.com.jef.agendav2.model.ItemModel
import br.com.jef.agendav2.model.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel(private val database: ItemDataBase) : ViewModel() {
    val itemLiveData = MutableLiveData<List<ItemModel>>()

    fun addItem(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val entity = ItemEntity(id = 0, name = name)
            database.itemDao().insert(entity)
            fetchAll()
        }
    }

    fun removeItem(item: ItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val item = item.toEntity()
            database.itemDao().delete(item)
            fetchAll()
        }
    }

    fun fetchAll() {
        val result = database.itemDao().getAll().map {
            it.toModel(onRemove = ::removeItem)
        }
        itemLiveData.postValue(result)
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            fetchAll()
        }
    }
}