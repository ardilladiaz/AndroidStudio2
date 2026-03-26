package com.cinthiadiaz.modulo5

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cinthiadiaz.modulo5.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val usuarioViewModel: UsuarioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usuarioViewModel.cargarDatosUsuario()

        binding.button4.setOnClickListener { startActivity(Intent(this, EnviarDinero::class.java)) }

        binding.button5.setOnClickListener { startActivity(Intent(this, IngresarDinero::class.java)) }

        binding.imageView28.setOnClickListener { startActivity(Intent(this, perfil::class.java)) }

        usuarioViewModel.usuarioLogueado.observe(this) { usuario ->
            if (usuario != null) {
                binding.textView30.text = "$${String.format("%.2f", usuario.Saldo ?: 0.0)}"
            }
        }
    }
}
