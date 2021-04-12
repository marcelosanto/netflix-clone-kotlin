package com.marcelo.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.marcelo.netflixclone.databinding.ActivityFormLoginBinding

class FormLogin : AppCompatActivity() {
    private lateinit var binding: ActivityFormLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        VerificarUsuarioLogado()

        binding.txtTelaCadastro.setOnClickListener {
            val intent = Intent(this, FormCadastro::class.java)
            startActivity(intent)
        }

        binding.btEntrar.setOnClickListener {
            val email = binding.editLoginEmail.text.toString()
            val senha = binding.editLoginPassword.text.toString()
            val mensagem = binding.mensagemErro

            if(email.isEmpty() || senha.isEmpty()) {
                mensagem.setText("Preencha os campos")
            }
            else {
                AutenticarUsuario()
            }
        }
    }

    private  fun AutenticarUsuario(){
        val email = binding.editLoginEmail.text.toString()
        val senha = binding.editLoginPassword.text.toString()
        val mensagem = binding.mensagemErro

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener {
            if(it.isSuccessful) {
                Toast.makeText(this, "Login efetuado com Sucesso!", Toast.LENGTH_SHORT).show()
                IrParaTelaDeFilmes()
            }
        }.addOnFailureListener {

            var erro = it

            when {
                erro is FirebaseAuthInvalidCredentialsException -> mensagem.setText("E-mail e Senha estão incorretos")
                erro is FirebaseNetworkException -> mensagem.setText("Sem conexão com a internet!")
                else -> mensagem.setText("Erro desconhecido")
            }

        }

    }

    private fun VerificarUsuarioLogado() {
        var usuarioLogado = FirebaseAuth.getInstance().currentUser

        if (usuarioLogado != null) {
            IrParaTelaDeFilmes()
        }
    }

    private fun IrParaTelaDeFilmes(){
        val intent = Intent(this, ListaFilmes::class.java)
        startActivity(intent)
        finish()
    }
}