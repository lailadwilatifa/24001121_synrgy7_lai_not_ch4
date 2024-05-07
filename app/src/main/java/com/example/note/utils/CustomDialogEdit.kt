package com.example.note.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.note.databinding.CustomDialogEditBinding
import com.example.note.db.entities.Note
import com.example.note.viewmodel.HomeViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CustomDialogEdit(context: Context, container: ViewGroup, viewModel: HomeViewModel, note: Note) {

    private var dialog: AlertDialog? = null

    init {
        val builder = AlertDialog.Builder(context)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = CustomDialogEditBinding.inflate(inflater, container, false)
        builder.setView(binding.root)
        builder.setCancelable(false)

        dialog = builder.create()

        binding.judulEditText.setText(note.judul)
        binding.catatanEditText.setText(note.catatan)

        binding.btnCancel.setOnClickListener {
            dialog?.dismiss()
        }

        binding.btnEdit.setOnClickListener {
            val judul = binding.judulEditText.text.toString()
            val catatan = binding.catatanEditText.text.toString()

            val currentTime = Date()
            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            val tanggalString = dateFormat.format(currentTime)

            if(judul.isNotEmpty() && catatan.isNotEmpty()){
                try {
                    val newNote = Note(
                        id = note.id,
                        judul = judul,
                        catatan = catatan,
                        tanggal = tanggalString
                    )
                    viewModel.update(newNote)
                    Toast.makeText(context, "Berhasil Edit Data", Toast.LENGTH_SHORT).show()
                    dialog?.dismiss()
                }catch (e: Exception){
                    Toast.makeText(context, "Gagal Edit Data", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(context, "ini kosong", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun show(){
        dialog?.show()
    }
}