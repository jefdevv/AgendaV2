package br.com.jef.agendav2.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.jef.agendav2.model.ItemModel

@Dao
interface ItemDao {
    @Insert
    suspend fun insert(item: ItemEntity): Long

    @Delete
    suspend fun delete(item: ItemEntity): Int

    @Query("select * from ItemEntity")
    fun getAll(): List<ItemEntity>
}