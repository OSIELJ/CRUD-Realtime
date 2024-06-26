package com.example.crudrealtimeadmin

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crudrealtimeadmin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnMainUpload.setOnClickListener {

            val intent = Intent(this@MainActivity,UploadActivity::class.java)
            startActivity(intent)
            finish()

        }

        binding.btnUpdate.setOnClickListener {

            val intent = Intent(this@MainActivity, UpdateActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnDelete.setOnClickListener {

            val intent = Intent(this@MainActivity, DeleteActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}