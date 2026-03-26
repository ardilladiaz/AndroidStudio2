package com.cinthiadiaz.modulo5

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cinthiadiaz.modulo5.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class sign_up : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(this).apply {
            setMessage("Creando cuenta...")
            setCanceledOnTouchOutside(false)
        }

        binding.button3.setOnClickListener { validarDatos() }

        binding.button2.setOnClickListener { finish() }
    }

    private fun validarDatos() {
        val nombre = binding.etNombre.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val pass = binding.etPassword.text.toString().trim()

        if (nombre.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches() && pass.length >= 6) {
            crearCuenta(email, pass, nombre)
        } else {
            Toast.makeText(this, "Revisa los datos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun crearCuenta(email: String, pass: String, nombre: String) {
        progressDialog.show()
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
            .addOnSuccessListener {
                val uid = firebaseAuth.uid ?: ""

                val datosUsuario = Usuario(uid, nombre, email, 0.0)

                FirebaseDatabase.getInstance().getReference("Usuarios").child(uid)
                    .setValue(datosUsuario).addOnSuccessListener {
                        progressDialog.dismiss()
                        startActivity(Intent(this, HomeEmpty::class.java))
                        finish()
                    }
            }
            .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
