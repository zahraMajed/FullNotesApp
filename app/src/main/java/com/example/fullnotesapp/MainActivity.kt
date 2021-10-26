package com.example.fullnotesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //my views
    lateinit var edNotes: EditText
    lateinit var btnSumbmit: Button
    //my variables
    var notesList= arrayListOf<Note>()
    val dbhelper=DataBaseHelper(this)
    var idPK=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edNotes=findViewById(R.id.edNots)
        btnSumbmit=findViewById(R.id.btnSubmit)

        btnSumbmit.setOnClickListener(){
            if (edNotes.text.isNotEmpty()){
                val note=edNotes.text.toString()
                idPK++
                val status = dbhelper.saveNote(idPK, note)
                Toast.makeText(applicationContext, "notes added successfully: $status", Toast.LENGTH_SHORT).show()
                getData()
            }
        }//end listener
        getData()
    }//end onCreate

    fun getData(){
        notesList.addAll(dbhelper.getData())
        rv_main.adapter=RecyclerAdapter(this, dbhelper.getData())
        rv_main.layoutManager= LinearLayoutManager(this)
    }

    fun alert(note: Note) {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Update note")

        val editNote = EditText(this)
        //old note will be shown to user in order to change it
        editNote.setText(note.Note)
        alert.setView(editNote)

        alert.setPositiveButton("Save") { _, _ ->
            dbhelper.update(Note(note.id, editNote.text.toString()))
            getData()
        }
        alert.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }
        alert.show()
    }

    fun DelData(note: Note){
        dbhelper.delData(note)
        getData()
    }

}//end class