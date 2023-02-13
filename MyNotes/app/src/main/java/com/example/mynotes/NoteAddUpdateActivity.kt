package com.example.mynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.example.mynotes.data.NoteDatabase
import com.example.mynotes.databinding.ActivityNoteAddUpdateBinding
import com.example.mynotes.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Calendar

class NoteAddUpdateActivity : AppCompatActivity() {
    private var binding : ActivityNoteAddUpdateBinding? = null

    private val db by lazy { NoteDatabase.getDatabase(this@NoteAddUpdateActivity).noteDao() }
    private var title:String = ""
    private var content :String = ""
    private var date:String = ""

    //var note : Note? = null

    var noteIntentData : Note? = null
    var isUpdate : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteAddUpdateBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        noteIntentData = intent.getParcelableExtra("noteData") // check for intent data

        if (noteIntentData != null){

            //this.note = noteIntentData
            isUpdate=true
            Toast.makeText(this, noteIntentData?.title, Toast.LENGTH_SHORT).show()
            binding?.apply {
                btnSubmit?.text = "Update"
                edtNoteTitle.setText(noteIntentData?.title, TextView.BufferType.EDITABLE )
                edtNoteContent.setText(noteIntentData?.content, TextView.BufferType.EDITABLE ) //bind data into edit text


                btnDelete?.visibility = View.VISIBLE
                btnDelete?.setOnClickListener {
                    lifecycleScope.launch{
                        db.deleteNote(noteIntentData!!)

                        finish()
                        noteIntentData = null
                        startActivity(Intent(this@NoteAddUpdateActivity, MainActivity::class.java)) //MOVE BACK TO THE MAIN ACTIVITY
                    }
                }
            }
        }

        binding?.btnCancel?.setOnClickListener{
            finish()
            super.onBackPressed()
        }

        binding?.btnSubmit?.setOnClickListener {

            if(validateForm()){
                getDataFromInput()

                if(isUpdate){
                    noteIntentData?.apply {
                        title = binding?.edtNoteTitle?.text.toString()//this.title
                        content = binding?.edtNoteContent?.text.toString()//this.content
                        date = getTodayDate()
                    }
                    lifecycleScope.launch{
                        db?.updateNote(noteIntentData!!)//INSERT THE FCKIN DATA
                        noteIntentData = null
                    }
                }else{
                    val note = Note(
                        id = null,
                        title = this.title,
                        content = this.content,
                        date = this.date
                    )

                    lifecycleScope.launch{
                        db?.insertNote(note) //INSERT THE FCKIN DATA
                    }
                }
                finish()
                startActivity(Intent(this@NoteAddUpdateActivity, MainActivity::class.java)) //MOVE BACK TO THE MAIN ACTIVITY
            }

        }

    }


    fun validateForm() : Boolean{
        when{
            binding?.edtNoteTitle?.text.isNullOrEmpty() -> {
                binding?.edtNoteTitle?.error = "judul is empty"
                return false
            }
            binding?.edtNoteContent?.text.isNullOrEmpty() -> {
                binding?.edtNoteContent?.error = "content is empty"
                return false
            }

            else -> return true
        }
    }

    fun getDataFromInput(){
        this.title= binding?.edtNoteTitle?.text.toString()
        this.content = binding?.edtNoteContent?.text.toString()
        this.date = getTodayDate()
    }

    fun getTodayDate(): String{
        var date =Calendar.getInstance().time
        var sdf = SimpleDateFormat("dd-MMM-yyyy")
        return sdf.format(date)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}