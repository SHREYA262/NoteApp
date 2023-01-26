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
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CreateNotesActivity2 : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_notes2)

        db = FirebaseFirestore.getInstance()

        val saveBtn = findViewById<TextView>(R.id.save_btn2)
        val title = findViewById<EditText>(R.id.titleEdt)
        val description = findViewById<EditText>(R.id.descriptionEdt)

        saveBtn.setOnClickListener {

            if (TextUtils.isEmpty(title.text?.trim().toString())) {
                title.error = "Field cannot be empty"
                title.requestFocus()
            } else if (TextUtils.isEmpty(description.text?.trim().toString())) {
                description.error = "Field cannot be empty"
                description.requestFocus()
            } else {
                val data = hashMapOf(
                    "Title" to title.text?.trim().toString(),
                    "Description" to description.text?.trim().toString()
                )
                db.collection("Notes")
                    .add(data)
                    .addOnSuccessListener { docRef ->
                        Log.d("Data Addition", "DocumentSnapshot written with ID: ${docRef}.id")
                        Toast.makeText(this, "Note added", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        Log.w("Data Addition", "Error adding document", e)
                    }
                startActivity(Intent(this, MainActivity2::class.java))
                finish()
            }
        }
    }
}