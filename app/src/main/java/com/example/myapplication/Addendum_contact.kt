package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText



class Addendum_contact: Activity() {

    private val list = mutableListOf<Todo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addendum_contact)
        val dbHelper = DBHelper(this)
        val editText = findViewById<EditText>(R.id.editText_name)
        val editTextName = findViewById<EditText>(R.id.editText_surname)
        val editTextDate = findViewById<EditText>(R.id.editText_birth)
        val editTextPhone = findViewById<EditText>(R.id.editText_phone)
        val buttonAdd = findViewById<Button>(R.id.button_back)
        val button = findViewById<Button>(R.id.button_cancell)

        buttonAdd.setOnClickListener {
            val title = editText.text.toString()
            val name = editTextName.text.toString()
            val date = editTextDate.text.toString()
            val phone = editTextPhone.text.toString()
            val id = dbHelper.addTodo(title,name,date,phone)
            list.add(Todo(id, title, name,date,phone))
            val intent = Intent(this@Addendum_contact, MainActivity::class.java)
            startActivity(intent)

        }

        button.setOnClickListener {
            val intent = Intent(this@Addendum_contact, MainActivity::class.java)
            startActivity(intent)
        }

    }
}