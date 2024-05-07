package com.example.note.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.note.viewmodel.HomeViewModel
import com.example.note.R
import com.example.note.adapters.HomeAdapter
import com.example.note.databinding.FragmentHomeBinding
import com.example.note.db.NoteDB
import com.example.note.db.entities.Note
import com.example.note.utils.CustomDialogDelete
import com.example.note.utils.CustomDialogEdit
import com.example.note.utils.CustomDialogInput
import com.example.note.utils.PreferenceManager
import com.example.note.viewmodel.ViewModelFactory

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private var containerView: ViewGroup? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        containerView = container

        val noteDao = NoteDB.getInstance(requireContext()).noteDao

        val preferenceManager = PreferenceManager(requireContext())

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(noteDao, preferenceManager, findNavController())
        )[HomeViewModel::class.java]

        binding.lifecycleOwner = this

        binding.viewmodel = viewModel
        binding.username = "Welcome, ${viewModel.getUsername()}!"

        val dialogInput = CustomDialogInput(requireContext(), container!!, viewModel)

        binding.btnAdd.setOnClickListener {
            dialogInput.show()
        }

        fetchData()

        return binding.root
    }

    private fun fetchData(){
        progressBar(true)
        viewModel.getAllNotes.observe(viewLifecycleOwner) { resources ->
            progressBar(false)
            if(resources != null){
                if(resources.isNotEmpty()){
                    dataKosong(false)
                    showRecyclerList(resources)
                }else{
                    dataKosong(true)
                }
            }else{
                dataKosong(true)
            }
        }
    }

    private fun showRecyclerList(data: List<Note>){
        val adapter = HomeAdapter(
            object : HomeAdapter.OnClickListener {
                override fun onClickItem(note: Note) {
                    val dialogDelete = CustomDialogDelete(requireContext(), containerView!!, viewModel, note)
                    dialogDelete.show()
                }
            },
            object : HomeAdapter.OnClickListener {
                override fun onClickItem(note: Note) {
                    val dialogEdit = CustomDialogEdit(requireContext(), containerView!!, viewModel, note)
                    dialogEdit.show()
                }
            }
        )

        adapter.submitData(data)
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvNotes.layoutManager = layoutManager
        binding.rvNotes.adapter = adapter
    }

    private fun progressBar(value: Boolean){
        binding.progresBar.visibility = if(value) View.VISIBLE else View.GONE
    }

    private fun dataKosong(value: Boolean){
        binding.tvDataKosong.visibility = if(value) View.VISIBLE else View.GONE
    }

}