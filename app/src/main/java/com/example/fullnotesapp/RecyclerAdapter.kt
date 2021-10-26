package com.example.fullnotesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view.view.*

class RecyclerAdapter(val activity: MainActivity, val notesList: ArrayList<Note>):RecyclerView.Adapter<RecyclerAdapter.itemViewHolder>(){
    class itemViewHolder (itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        return itemViewHolder(
            LayoutInflater.from(parent.context).inflate(
            R.layout.item_view,parent,false
        ))
    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        val noteObject=notesList[position]
        val id=notesList[position].id
        var note=notesList[position].Note

        holder.itemView.apply {
            tvNoteNum.text="Note $id: "
            tvNote.text=note

            EditActionButton.setOnClickListener(){
                activity.alert(noteObject)
            }

            DelActionButton.setOnClickListener(){
                activity.DelData(noteObject)
            }
        }
    }

    override fun getItemCount(): Int =notesList.size

}