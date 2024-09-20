package br.com.jef.agendav2.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.jef.agendav2.R
import br.com.jef.agendav2.model.ItemModel

class ItemAdapter : RecyclerView.Adapter<ItemViewHolder>() {
    private var listItem = listOf<ItemModel>()

    fun updateItem(newItem: List<ItemModel>) {
        listItem = newItem
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = listItem[position]
        holder.bind(item)
    }

}

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val txtItem = view.findViewById<TextView>(R.id.txt_item_id)
    val imgRemove = view.findViewById<ImageView>(R.id.img_remove_id)

    fun bind(item: ItemModel) {
        txtItem.text = item.name
        imgRemove.setOnClickListener() {
            item.onRemove(item)
        }

    }

}
