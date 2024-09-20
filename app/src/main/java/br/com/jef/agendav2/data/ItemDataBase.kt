package br.com.jef.agendav2.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ItemEntity::class], version = 1)
abstract class ItemDataBase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}