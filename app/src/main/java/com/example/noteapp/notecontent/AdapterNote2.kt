package com.example.noteapp.notecontent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R

class AdapterNote2 (private val itemList : ArrayList<noteData2>): RecyclerView.Adapter<AdapterNote2.Myviewholder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterNote2.Myviewholder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_item2, parent, false)
        return AdapterNote2.Myviewholder(itemView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: AdapterNote2.Myviewholder, position: Int) {
        val currentItem = itemList[position]
        holder.noteTitle.text = currentItem.Title
        holder.noteDes.text = currentItem.Description
    }

    class Myviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val noteTitle: TextView = itemView.findViewById(R.id.title)
        val noteDes: TextView = itemView.findViewById(R.id.description)
    }
}