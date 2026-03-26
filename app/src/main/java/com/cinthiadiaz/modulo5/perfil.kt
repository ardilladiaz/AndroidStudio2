package com.cinthiadiaz.modulo5

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cinthiadiaz.modulo5.databinding.ActivityPerfilBinding
import com.google.firebase.auth.FirebaseAuth

class perfil : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding
    private val usuarioViewModel: UsuarioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usuarioViewModel.cargarDatosUsuario()

        usuarioViewModel.usuarioLogueado.observe(this) { usuario ->
            if (usuario != null) {
                binding.textViewSaludoPerfil.text = usuario.Nombre
                binding.textViewEmailPerfil.text = usuario.Email
            }
        }

        binding.button10.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginSignUp::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        binding.button7.setOnClickListener { finish() }
    }
}
