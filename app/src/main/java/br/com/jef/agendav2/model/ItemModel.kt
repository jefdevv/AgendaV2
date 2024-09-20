package br.com.jef.agendav2.model

import br.com.jef.agendav2.data.ItemEntity

class ItemModel(val id: Long, val name: String, val onRemove: (ItemModel) -> Unit)


fun ItemModel.toEntity(): ItemEntity {
    return ItemEntity(
        id = this.id,
        name = this.name
    )
}