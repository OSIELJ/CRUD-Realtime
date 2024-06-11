package com.example.crudrealtimeadmin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crudrealtimeadmin.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnDeleteButton.setOnClickListener {
            val placaMoto = binding.edtPlacaMoto.text.toString()
            if (placaMoto.isNotEmpty()){
                deleteData(placaMoto)
            } else{
                Toast.makeText(this, "Por favor informe a placa da moto", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun deleteData(placaMoto: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Informção das motos")
        databaseReference.child(placaMoto).removeValue().addOnSuccessListener {
            binding.edtPlacaMoto.text.clear()
            Toast.makeText(this, "Dados da placa: $placaMoto deletado.", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Dados não pode ser deletado", Toast.LENGTH_SHORT).show()

        }

    }
}