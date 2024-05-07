package com.example.note.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.note.databinding.CustomDialogDeleteBinding
import com.example.note.db.entities.Note
import com.example.note.viewmodel.HomeViewModel

class CustomDialogDelete(context: Context, container: ViewGroup, viewModel: HomeViewModel, note: Note) {

    private var dialog: AlertDialog? = null

    init {
        val builder = AlertDialog.Builder(context)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = CustomDialogDeleteBinding.inflate(inflater, container, false)
        builder.setView(binding.root)
        builder.setCancelable(false)

        dialog = builder.create()

        binding.tvJudul.text = note.judul

        binding.btnCancel.setOnClickListener {
            dialog?.dismiss()
        }

        binding.btnHapus.setOnClickListener {
            try {
                viewModel.deleteNote(note.id)
                Toast.makeText(context, "Berhasil Hapus Data", Toast.LENGTH_SHORT).show()
                dialog?.dismiss()
            }catch (e: Exception){
                Toast.makeText(context, "Gagal Hapus Data", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun show(){
        dialog?.show()
    }
}