package com.example.mynotes.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.NoteAddUpdateActivity
import com.example.mynotes.databinding.ItemNoteLayoutBinding
import com.example.mynotes.model.Note

class NoteAdapter() :RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var  listNote : ArrayList<Note> = arrayListOf()
    fun setData(newlistNote: List<Note>){
        listNote.apply {
            clear()
            addAll(newlistNote)
        }
        notifyDataSetChanged()
    }
    inner class NoteViewHolder(var noteLayoutBinding: ItemNoteLayoutBinding): RecyclerView.ViewHolder(noteLayoutBinding.root) {
        fun bind(item: Note) {
            noteLayoutBinding.apply {
                tvNoteTitle.text = item.title
                tvNoteDate.text = item.date
                btnEdit.setOnClickListener {
                    val intent = Intent(itemView.context.applicationContext, NoteAddUpdateActivity::class.java)
                    intent.putExtra("noteData" , item) // pass the data
                    itemView.context.startActivity(intent)
                    //Toast.makeText(itemView.context, item.title, Toast.LENGTH_SHORT).show()
                }
                root.setOnClickListener {
                    Toast.makeText(itemView.context, item.title, Toast.LENGTH_SHORT).show()

                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = listNote[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return listNote.size
    }
}