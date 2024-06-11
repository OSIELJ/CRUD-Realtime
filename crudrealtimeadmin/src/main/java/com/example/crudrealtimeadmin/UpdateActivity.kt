package com.example.crudrealtimeadmin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crudrealtimeadmin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnUpdateButton.setOnClickListener {
            val placaMoto = binding.edtPlacaMoto.text.toString()
            val nomeDonoAtualizado = binding.edtDonoMoto.text.toString()
            val marcaMotoAtualizado = binding.edtMarcaMoto.text.toString()
            val modeloMotoAtualizado = binding.edtModeloMoto.text.toString()

            updateData(placaMoto, nomeDonoAtualizado, marcaMotoAtualizado, modeloMotoAtualizado)
        }

    }

    private fun updateData(placaMoto: String, nomeDono: String, marcaMoto: String, modeloMoto: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Informção das motos")

        val dadoMoto = mapOf<String, String>("nomeDono" to nomeDono, "marcaMoto" to marcaMoto, "modeloMoto" to modeloMoto)
        databaseReference.child(placaMoto).updateChildren(dadoMoto).addOnSuccessListener {
            binding.edtPlacaMoto.text.clear()
            binding.edtDonoMoto.text.clear()
            binding.edtMarcaMoto.text.clear()
            binding.edtModeloMoto.text.clear()

            Toast.makeText(this, "Dados Atualizado", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Dados não Atualizado", Toast.LENGTH_SHORT).show()

        }

    }
}