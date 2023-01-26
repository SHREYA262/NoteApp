package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.notecontent.AdapterNote
import com.example.noteapp.notecontent.AdapterNote2
import com.example.noteapp.notecontent.noteData
import com.example.noteapp.notecontent.noteData2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.*
import com.google.firebase.firestore.Query

class MainActivity2 : AppCompatActivity() {
    private lateinit var db : FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var noteArrayList: ArrayList<noteData2>
    private lateinit var adapter : AdapterNote2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val composeNote= findViewById<FloatingActionButton>(R.id.compose_note2)
        composeNote.setOnClickListener {
            val intent = Intent(this@MainActivity2, CreateNotesActivity2::class.java)
            startActivity(intent)
        }

        supportActionBar?.hide()

        recyclerView= findViewById<RecyclerView>(R.id.recyclerview2)
        recyclerView.layoutManager= LinearLayoutManager(this)
        noteArrayList = arrayListOf<noteData2>()

        getUserData()

    }
    private fun getUserData()
    {
        db= FirebaseFirestore.getInstance()
        db.collection("Notes")
            .addSnapshotListener(object : EventListener<QuerySnapshot>
            {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?)
                {
                    if(error!=null)
                    {
                        Log.e("Firestore Error",error.message.toString())
                        return
                    }
                    for(dc: DocumentChange in value?.documentChanges!!){
                        if(dc.type == DocumentChange.Type.ADDED){

                            noteArrayList.add(dc.document.toObject(noteData2::class.java))
                        }
                    }
                    adapter = AdapterNote2(noteArrayList)
                    recyclerView.adapter = adapter
                    recyclerView.setHasFixedSize(true)
                    recyclerView.setHasFixedSize(true)
                    adapter.notifyDataSetChanged()
                }
            })
        }
    }
