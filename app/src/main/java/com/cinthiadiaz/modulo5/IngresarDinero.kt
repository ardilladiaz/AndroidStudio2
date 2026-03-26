package com.cinthiadiaz.modulo5

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cinthiadiaz.modulo5.databinding.ActivityIngresarDineroBinding

class IngresarDinero : AppCompatActivity() {

    private lateinit var binding: ActivityIngresarDineroBinding
    private val usuarioViewModel: UsuarioViewModel by viewModels()
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIngresarDineroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this).apply {
            setMessage("Ingresando fondos...")
            setCanceledOnTouchOutside(false)
        }

        binding.button3.setOnClickListener {
            val monto = binding.etMonto.text.toString().toDoubleOrNull() ?: 0.0
            if (monto > 0) {
                progressDialog.show()
                usuarioViewModel.sumarSaldo(monto,
                    onSuccess = {
                        progressDialog.dismiss()
                        Toast.makeText(this, "Depósito exitoso", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    },
                    onFailure = { error ->
                        progressDialog.dismiss()
                        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                    }
                )
            } else {
                binding.etMonto.error = "Monto inválido"
            }
        }

        binding.button6.setOnClickListener { finish() }
    }
}
