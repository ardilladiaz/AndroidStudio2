package com.cinthiadiaz.modulo5

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cinthiadiaz.modulo5.databinding.ActivityHomeEmptyBinding

class HomeEmpty : AppCompatActivity() {

    private lateinit var binding: ActivityHomeEmptyBinding
    private val usuarioViewModel: UsuarioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeEmptyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usuarioViewModel.cargarDatosUsuario()

        binding.button4.setOnClickListener { startActivity(Intent(this, EnviarDinero::class.java)) }
        binding.button5.setOnClickListener { startActivity(Intent(this, IngresarDinero::class.java)) }

        usuarioViewModel.usuarioLogueado.observe(this) { usuario ->
            if (usuario != null && (usuario.Saldo ?: 0.0) > 0.0) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }
    }
}
