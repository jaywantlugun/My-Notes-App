package com.example.mynotesapp.Repository

import androidx.lifecycle.LiveData
import com.example.mynotesapp.Dao.NotesDao
import com.example.mynotesapp.Model.Notes

class NotesRepository(val dao:NotesDao) {

    fun getAllNotes():LiveData<List<Notes>>{
        return dao.getNotes()
    }

    fun getLowNotes():LiveData<List<Notes>>{
        return dao.getLowNotes()
    }

    fun getMediumNotes():LiveData<List<Notes>>{
        return dao.getMediumNotes()
    }

    fun getHighNotes():LiveData<List<Notes>>{
        return dao.getHighNotes()
    }


    fun insertNotes(notes: Notes){
        dao.insertNotes(notes)
    }

    fun deleteNotes(id:Int){
        dao.deleteNotes(id)
    }

    fun updateNotes(notes:Notes){
        dao.updateNotes(notes)
    }


}