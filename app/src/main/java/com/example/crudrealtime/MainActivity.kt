package com.example.crudrealtime

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crudrealtime.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference

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

        binding.btnSearchButton.setOnClickListener {
            val placaMoto : String = binding.edtPlacaMoto.text.toString()
            if (placaMoto.isNotEmpty()){
                readData(placaMoto)
            }else {
                Toast.makeText(this, "Por favor informe uma placa", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun readData(placaMoto : String ){

        databaseReference = FirebaseDatabase.getInstance().getReference("Informção das motos")
        databaseReference.child(placaMoto).get().addOnSuccessListener { it ->
            if (it.exists()){
                val nomeDono = it.child("nomeDono").value
                val marcaMoto = it.child("marcaMoto").value
                val modeloMoto = it.child("modeloMoto").value

                Toast.makeText(this, "Placa encontrada", Toast.LENGTH_SHORT).show()

                binding.edtPlacaMoto.text.clear()
                "Proprietario: ${nomeDono.toString()}".also { binding.tvDonoMoto.text = it }
                "Marca da moto: ${marcaMoto.toString()}".also { binding.tvMarcaMoto.text = it }
                "Modelo da moto: ${modeloMoto.toString()}".also { binding.tvModeloMoto.text = it }

            }else{

                Toast.makeText(this, "Placa da moto não encontrada", Toast.LENGTH_SHORT).show()

            }

        }.addOnFailureListener {
            Toast.makeText(this, "Algo deu errado", Toast.LENGTH_SHORT).show()

        }


    }

}