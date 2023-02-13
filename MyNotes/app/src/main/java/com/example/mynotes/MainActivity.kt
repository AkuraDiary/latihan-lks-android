package com.example.mynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynotes.adapter.NoteAdapter
import com.example.mynotes.data.NoteDatabase
import com.example.mynotes.databinding.ActivityMainBinding
import com.example.mynotes.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var mainBinding: ActivityMainBinding? = null
    private val db by lazy { NoteDatabase.getDatabase(this@MainActivity).noteDao() }

    var noteAdapter = NoteAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //db = NoteDatabase.getDatabase(this@MainActivity) // get the database


        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding?.root)

        mainBinding?.apply {
            fabAddNote.setOnClickListener {
                val addNewNoteintent = Intent(this@MainActivity, NoteAddUpdateActivity::class.java)
                startActivity(addNewNoteintent)
            }
        }

        db?.getAllNotes()?.observe(this) {
            noteAdapter.setData(it) //GET THE FCKIN DATA AND FKIN OBSERVE IT
            Log.d("DATA FROM DB", it.toString())
        }

        mainBinding?.apply {
            rvListNote.apply {
                setHasFixedSize(true)
                adapter = noteAdapter

                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }


    }



    override fun onDestroy() {
        super.onDestroy()
        mainBinding = null
    }


}