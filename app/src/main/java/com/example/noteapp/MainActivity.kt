package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.notecontent.AdapterNote
import com.example.noteapp.notecontent.noteData
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var dbrf: DatabaseReference
    private lateinit var recyclerView: RecyclerView
    private lateinit var noteArrayList: ArrayList<noteData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val composeNote = findViewById<FloatingActionButton>(R.id.compose_note)
        composeNote.setOnClickListener {
            val intent = Intent(this@MainActivity, CreateNotesActivity::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.setHasFixedSize(true)

        noteArrayList = arrayListOf<noteData>()

        getNotesData()

        dbrf = FirebaseDatabase.getInstance().getReference("Notes")
    }

    private fun getNotesData() {
        dbrf = FirebaseDatabase.getInstance().getReference("Notes")
        dbrf.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val detail = userSnapshot.getValue(noteData::class.java)
                        noteArrayList.add(detail!!)
//                        recyclerView.adapter = AdapterNote(noteArrayList)
                    }
                    val adapter = AdapterNote(noteArrayList)
                    recyclerView.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}