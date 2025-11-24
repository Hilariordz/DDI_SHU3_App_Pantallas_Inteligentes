package com.tuusuario.pantallasinteligentes.data.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String?,
    val poster_path: String?,
    val backdrop_path: String?
)

data class MovieResponse(
    val page: Int,
    val results: List<Movie>
)
