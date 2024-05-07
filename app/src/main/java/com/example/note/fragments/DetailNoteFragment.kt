package com.example.note.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.note.R
import com.example.note.databinding.FragmentDetailNoteBinding

class DetailNoteFragment : Fragment() {
    private lateinit var binding: FragmentDetailNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_note, container, false)

        val note = DetailNoteFragmentArgs.fromBundle(arguments as Bundle).note

        binding.tanggal = note?.tanggal
        binding.judul = note?.judul
        binding.catatan = note?.catatan

        binding.btnBack.setOnClickListener {
            view?.findNavController()?.popBackStack()
        }

        return binding.root
    }
}