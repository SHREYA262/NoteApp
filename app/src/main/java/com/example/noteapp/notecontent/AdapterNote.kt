package com.example.noteapp.notecontent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R

class AdapterNote (private val itemList : ArrayList<noteData>): RecyclerView.Adapter<AdapterNote.Myviewholder> () {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myviewholder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)

        return Myviewholder(itemView)
    }

    override fun onBindViewHolder(holder: Myviewholder, position: Int) {


        val currentItem = itemList[position]
        holder.noteTitle.text = currentItem.note_Title
        holder.noteDes.text = currentItem.note_Des
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class Myviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val noteTitle: TextView = itemView.findViewById(R.id.title)
        val noteDes: TextView = itemView.findViewById(R.id.description)


    }
}