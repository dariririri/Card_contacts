package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import dialogYesOrNo


/*
fun dialogYesOrNo(
    activity: Activity,
    title: String,
    message: String,
    listener: DialogInterface.OnClickListener
) {
    val builder = AlertDialog.Builder(activity)
    builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
        dialog.dismiss()
        listener.onClick(dialog, id)
    })
    builder.setNegativeButton("No", null)
    val alert = builder.create()
    alert.setTitle(title)
    alert.setMessage(message)
    alert.show()
}*/


class Card_contact: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_contact)
        title = "Карточка Контакта"
        val dbHelper = DBHelper(this)
        val Text = findViewById<TextView>(R.id.textView_surname)
        val TextName = findViewById<TextView>(R.id.textView_name)
        val TextDate = findViewById<TextView>(R.id.textView_birth)
        val TextPhone = findViewById<TextView>(R.id.textView_phone)
        val button = findViewById<Button>(R.id.button_back)
        val buttonDrop = findViewById<Button>(R.id.button_delete)
        val buttonChange = findViewById<Button>(R.id.button_change)
        val id = intent.getLongExtra("Id",0)

        val objects = dbHelper.getById(id)

        Text.text = "Фамилия:  ${objects?.title}"
        TextName.text = "Имя:      " + objects?.name
        TextDate.text = "День рождения:" + objects?.date
        TextPhone.text = "Телефон:   " + objects?.telephone

        buttonChange.setOnClickListener {

            val intent = Intent(this@Card_contact, Change_contact::class.java)
            intent.putExtra("Id", id)
            startActivity(intent)
        }
        TextPhone.setOnClickListener {
            //val dialIntent = Intent(Intent.ACTION_DIAL)
            //dialIntent.data = Uri.parse("tel:"+objects?.telephone)
            //startActivity(dialIntent)
            val REQUEST_CODE=1
            if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE), REQUEST_CODE)

            } else {

                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:"+objects?.telephone)
                startActivity(callIntent)

            }
        }
        buttonDrop.setOnClickListener {

            val uid = id
            dialogYesOrNo(
                this,
                "Вопрос",
                "Вы уверены,что хотите удалить это контакт?",
                DialogInterface.OnClickListener { dialog, id ->
                    dbHelper.remove(uid)
                    val intent = Intent(this@Card_contact, MainActivity::class.java)
                    startActivity(intent)
                })

        }

        button.setOnClickListener {
            val intent = Intent(this@Card_contact, MainActivity::class.java)
            startActivity(intent)
        }


    }
}