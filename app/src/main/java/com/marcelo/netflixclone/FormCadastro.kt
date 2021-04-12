package com.marcelo.netflixclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.marcelo.netflixclone.databinding.ActivityFormCadastroBinding

class FormCadastro : AppCompatActivity() {
    private lateinit var binding: ActivityFormCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        Toolbar()

        binding.btCadastrar.setOnClickListener {
            var email = binding.editCadastroEmail.text.toString()
            var senha = binding.editCadastroSenha.text.toString()
            var mensagem = binding.mensagemErroCadastro

            if(email.isEmpty() || senha.isEmpty()){
                mensagem.setText("Preencha todos os campos")
            } else {
                CadastrarUsuario()
            }
        }

    }

    private  fun CadastrarUsuario(){
        val email = binding.editCadastroEmail.text.toString()
        val senha = binding.editCadastroSenha.text.toString()
        val mensagem = binding.mensagemErroCadastro

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                binding.editCadastroEmail.setText("")
                binding.editCadastroSenha.setText("")
                binding.mensagemErroCadastro.setText("")
            }
        }.addOnFailureListener {

            var erro = it

            when{
                erro is FirebaseAuthWeakPasswordException -> mensagem.setText("Senha precisa ter no minimo 6 caracteres.")
                erro is FirebaseAuthUserCollisionException -> mensagem.setText("Email ja cadastrado.")
                erro is FirebaseNetworkException -> mensagem.setText("Não conectado a internet.")
                else -> mensagem.setText("Erro ao cadastrar usuário.")
            }


        }
    }

    private  fun Toolbar(){
        val toolbar = binding.toolbarCadastro
        toolbar.setBackgroundColor(getColor(R.color.white))
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_netflix_official_logo))
    }
}