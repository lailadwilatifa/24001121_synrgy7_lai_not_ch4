package com.example.note.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.note.databinding.ItemNoteBinding
import com.example.note.db.entities.Note
import com.example.note.fragments.HomeFragmentDirections

class HomeAdapter(
    private val onDeleteClick: OnClickListener,
    private val onUpdateClick: OnClickListener
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(
            oldItem: Note,
            newItem: Note
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Note,
            newItem: Note
        ): Boolean = oldItem.hashCode() == newItem.hashCode()
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitData(value: List<Note>?) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemNoteBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        val data = differ.currentList[position]
        data.let { holder.bind(data) }
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Note){
            binding.apply {
                tvJudul.text = data.judul
                tvTanggal.text = data.tanggal
                btnUpdate.setOnClickListener {
                    onUpdateClick.onClickItem(data)
                }
                btnDelete.setOnClickListener {
                    onDeleteClick.onClickItem(data)
                }
                root.setOnClickListener {
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailNoteFragment(data)
                    action.note = data
                    it.findNavController().navigate(action)
                }
            }
        }
    }

    interface OnClickListener {
        fun onClickItem(note: Note)
    }
}