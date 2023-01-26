package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SelectDatabase : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_database)

        val button1 = findViewById<Button>(R.id.cfBtn)
        val button2 = findViewById<Button>(R.id.rdBtn)

        button1.setOnClickListener {

            startActivity(Intent(this, MainActivity2::class.java))

        }
        button2.setOnClickListener {

            startActivity(Intent(this, MainActivity::class.java))

        }

    }
}