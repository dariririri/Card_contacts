package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText



class Change_contact: Activity() {


    private val list = mutableListOf<Todo>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_contact)
        val dbHelper = DBHelper(this)
        val button = findViewById<Button>(R.id.button_back)

        val editText = findViewById<EditText>(R.id.editText_name)
        val editTextName = findViewById<EditText>(R.id.editText_surname)
        val editTextDate = findViewById<EditText>(R.id.editText_birth)
        val editTextPhone = findViewById<EditText>(R.id.editText_phone)


        val intent = intent
        val id = intent.getLongExtra("Id",0)
        val allData = dbHelper.getById(id)

        editText.setText(allData?.title)
        editTextName.setText(allData?.name)
        editTextDate.setText(allData?.date)
        editTextPhone.setText(allData?.telephone)

        val buttonChange = findViewById<Button>(R.id.button_change)
        buttonChange.setOnClickListener {
            val title = editText.text.toString()
            val name = editTextName.text.toString()
            val date = editTextDate.text.toString()
            val phone = editTextPhone.text.toString()
            val cool = dbHelper.update(id.toString().toLong(),title,name,date,phone)
            list.add(Todo(id.toString().toLong(),title,name,date,phone))
            val intent = Intent(this@Change_contact, MainActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            val intent = Intent(this@Change_contact, MainActivity::class.java)
            startActivity(intent)
        }

    }
}