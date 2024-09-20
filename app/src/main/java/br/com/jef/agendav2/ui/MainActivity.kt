package br.com.jef.agendav2.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import br.com.jef.agendav2.R
import br.com.jef.agendav2.viewmodel.ItemViewModel
import br.com.jef.agendav2.viewmodel.ItemViewModelFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtItem = findViewById<EditText>(R.id.edt_item_id)
        val btnSave = findViewById<Button>(R.id.btn_save_id)
        val recycler = findViewById<RecyclerView>(R.id.recycler_id)
        val adapter = ItemAdapter()

        recycler.adapter = adapter

        val viewModel: ItemViewModel by viewModels() {
            ItemViewModelFactory(applicationContext)
        }

        btnSave.setOnClickListener() {
            if (edtItem.text.isEmpty()) {
                edtItem.error = "Campo Vazio"
                return@setOnClickListener
            }
            viewModel.addItem(edtItem.text.toString())
            edtItem.text.clear()

        }

        viewModel.itemLiveData.observe(this) { item ->
            adapter.updateItem(item)
        }
    }


}
