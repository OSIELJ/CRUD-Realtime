package com.example.crudrealtimeadmin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crudrealtimeadmin.databinding.ActivityMainBinding
import com.example.crudrealtimeadmin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnUploadButton.setOnClickListener {
            val donoMoto = binding.edtDonoMoto.text.toString()
            val marcaMoto = binding.edtMarcaMoto.text.toString()
            val placaMoto = binding.edtPlacaMoto.text.toString()
            val modeloMoto = binding.edtModeloMoto.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("Informção das motos")
            val dadosMoto = DadosMoto(donoMoto, marcaMoto, placaMoto, modeloMoto)
            databaseReference.child(placaMoto).setValue(dadosMoto).addOnSuccessListener {

                binding.edtDonoMoto.text.clear()
                binding.edtMarcaMoto.text.clear()
                binding.edtPlacaMoto.text.clear()
                binding.edtModeloMoto.text.clear()

                Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@UploadActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {

                Toast.makeText(this, "Falha ao salvar", Toast.LENGTH_SHORT).show()

            }


        }

    }
}