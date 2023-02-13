package com.example.mynotes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mynotes.model.Note

@Database(entities = [Note::class], version = 2 )
abstract class NoteDatabase : RoomDatabase(){
    abstract fun noteDao() : NoteDao

    companion object{
        @Volatile
        private var INSTANCE : NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase{

            var mInstance = INSTANCE
            if(mInstance != null){
                return  mInstance
            }
            synchronized(NoteDatabase::class){
                return Room.databaseBuilder(
                    context.applicationContext, NoteDatabase::class.java, "noteDb"
                ).fallbackToDestructiveMigration().build()
            }
        }
    }

}