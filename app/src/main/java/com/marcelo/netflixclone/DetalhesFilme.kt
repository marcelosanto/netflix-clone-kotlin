package com.marcelo.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.marcelo.netflixclone.Adapter.FilmesAdapter
import com.marcelo.netflixclone.Model.addFilmes
import com.marcelo.netflixclone.databinding.ActivityDetalhesFilmeBinding
import com.squareup.picasso.Picasso

class DetalhesFilme : AppCompatActivity() {

    private lateinit var binding: ActivityDetalhesFilmeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesFilmeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        Toolbar()

        val recycler_outros_filmes = binding.recyclerOutrosFilmes
        recycler_outros_filmes.adapter = FilmesAdapter(addFilmes())
        recycler_outros_filmes.layoutManager = GridLayoutManager(applicationContext, 3)

        val capaTheWitcher = "https://firebasestorage.googleapis.com/v0/b/netflix-clone-c5ff9.appspot.com/o/video.jpg?alt=media&token=940da3e1-2b94-4729-a402-071f5c01f0eb"
        Picasso.get().load(capaTheWitcher).fit().into(binding.capaDetalhe)

        binding.playVideo.setOnClickListener {
            val intent = Intent(this, Video::class.java)
            startActivity(intent)
        }

    }

    private  fun Toolbar() {
        val toolbar_detalhes = binding.toolbarDetalhes
        toolbar_detalhes.setNavigationIcon(getDrawable(R.drawable.ic_voltar))
        toolbar_detalhes.setNavigationOnClickListener {
            val intent = Intent(this, ListaFilmes::class.java)
            startActivity(intent)
            finish()
        }
    }
}