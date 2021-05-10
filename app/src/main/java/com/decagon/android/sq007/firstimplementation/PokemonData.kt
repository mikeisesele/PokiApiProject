package com.decagon.android.sq007.firstimplementation

import com.decagon.android.sq007.firstimplementation.pokemondataentry.PokemonDataResult

data class PokemonData(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PokemonDataResult>
)