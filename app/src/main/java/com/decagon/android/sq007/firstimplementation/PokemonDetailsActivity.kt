package com.decagon.android.sq007.firstimplementation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.decagon.android.sq007.R
import com.decagon.android.sq007.databinding.ActivityPokemonDetailsBinding
import com.decagon.android.sq007.databinding.ActivityPokemonDisplayBinding

class PokemonDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityPokemonDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPokemonDetailsBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)


    }





}