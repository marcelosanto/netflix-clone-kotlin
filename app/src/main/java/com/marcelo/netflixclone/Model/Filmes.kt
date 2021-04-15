package com.marcelo.netflixclone.Model

data class Filmes (
        val capaFilme: Int
        )

class  FilmesBuilder{
    var capaFilme: Int = 0
    fun build(): Filmes = Filmes(capaFilme)
}

fun filmes(block: FilmesBuilder.() -> Unit): Filmes = FilmesBuilder().apply(block).build()

fun addFilmes(): MutableList<Filmes> = mutableListOf() {
   // filmes {  }
}
