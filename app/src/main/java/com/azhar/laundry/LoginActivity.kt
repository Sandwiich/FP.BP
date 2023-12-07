package com.azhar.laundry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.azhar.laundry.view.main.MainActivity
import com.azhar.laundry.view.main.MainAdapter

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //instance
        val btnLogin: Button = findViewById(R.id.buttonLogin)
        //event
        btnLogin.setOnClickListener {
            val intentHome = Intent(this,MainActivity::class.java)
            startActivity(intentHome)
        }
    }
}