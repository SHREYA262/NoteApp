package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.noteapp.notecontent.noteData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CreateNotesActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private var name :String ?= null
    private lateinit var db: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_notes)

        val noteTitle=findViewById<EditText>(R.id.editText1)
        val noteDes= findViewById<EditText>(R.id.editText2)
        val save_btn=findViewById<TextView>(R.id.save_btn)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser
        name = user?.displayName

        save_btn.setOnClickListener {

            val notes_details = noteData(
                noteTitle.text.toString(),
                noteDes?.text.toString()
            )

            db = FirebaseDatabase.getInstance().getReference("Notes")


            db.child(name.toString()).setValue(notes_details).addOnSuccessListener {

                noteTitle.text?.clear()
                noteDes.text?.clear()



                Toast.makeText(this, "added", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

            }

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }



    }
}