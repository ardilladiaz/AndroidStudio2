package com.cinthiadiaz.modulo5

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cinthiadiaz.modulo5.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(this).apply {
            setMessage("Iniciando sesión...")
            setCanceledOnTouchOutside(false)
        }

        binding.button3.setOnClickListener { validarDatos() }

        binding.button2.setOnClickListener { startActivity(Intent(this, sign_up::class.java)) }
    }

    private fun validarDatos() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.error = "Email no válido"
        } else if (password.isEmpty()) {
            binding.etPassword.error = "Ingrese contraseña"
        } else {
            loginUser(email, password)
        }
    }

    private fun loginUser(email: String, pass: String) {
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, pass)
            .addOnSuccessListener {
                progressDialog.dismiss()
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
            .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
