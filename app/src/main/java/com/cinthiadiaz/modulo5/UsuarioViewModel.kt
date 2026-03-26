package com.cinthiadiaz.modulo5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UsuarioViewModel : ViewModel() {

    private val _usuarioLogueado = MutableLiveData<Usuario?>()
    val usuarioLogueado: LiveData<Usuario?> get() = _usuarioLogueado

    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().getReference("Usuarios")

    fun cargarDatosUsuario() {
        val uid = auth.uid ?: return
        database.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val usuario = snapshot.getValue(Usuario::class.java)
                _usuarioLogueado.postValue(usuario)
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun sumarSaldo(monto: Double, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val uid = auth.uid ?: return
        val currentSaldo = _usuarioLogueado.value?.Saldo ?: 0.0
        val nuevoSaldo = currentSaldo + monto
        database.child(uid).child("Saldo").setValue(nuevoSaldo)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it.message ?: "Error al sumar saldo") }
    }

    fun restarSaldo(monto: Double, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val uid = auth.uid ?: return
        val currentSaldo = _usuarioLogueado.value?.Saldo ?: 0.0
        if (currentSaldo >= monto) {
            val nuevoSaldo = currentSaldo - monto
            database.child(uid).child("Saldo").setValue(nuevoSaldo)
                .addOnSuccessListener { onSuccess() }
                .addOnFailureListener { onFailure(it.message ?: "Error al restar saldo") }
        } else {
            onFailure("Saldo insuficiente")
        }
    }
}
