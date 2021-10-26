package com.example.fullnotesapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context?) : SQLiteOpenHelper(context, "data.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL("create table Notes (ID INTEGER PRIMARY KEY,Note Text Not null)")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun saveNote(id:Int, note:String): Long {
        val ConVal= ContentValues()
        ConVal.put("ID", id)
        ConVal.put("Note", note)
        val sqlite:SQLiteDatabase=writableDatabase
        val status= sqlite.insert("Notes",null,ConVal)
        return status
    }

    @SuppressLint("Range")
    fun getData(): ArrayList<Note> {

        var data= arrayListOf<Note>()
        val sqlite:SQLiteDatabase=readableDatabase

        val cursor: Cursor =sqlite.query("Notes" , null,null, null, null, null,null)
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            val id= cursor.getString(cursor.getColumnIndex("ID"))
            val note=cursor.getString(cursor.getColumnIndex("Note"))
            data.add(Note(id,note))
            cursor.moveToNext()
        }
        return data
    }

    fun update(note: Note) {
        val ConVal = ContentValues()
        ConVal.put("ID", note.id)
        ConVal.put("Note", note.Note)
        this.writableDatabase.update("Notes", ConVal, "ID=${note.id}", null)
    }

    fun delData(note: Note){
        this.writableDatabase.delete("Notes","ID=${note.id}", null)
    }

}//end class