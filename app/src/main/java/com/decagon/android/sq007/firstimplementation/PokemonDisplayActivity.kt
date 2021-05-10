package com.decagon.android.sq007.firstimplementation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.decagon.android.sq007.databinding.ActivityPokemonDisplayBinding
import com.decagon.android.sq007.firstimplementation.Retrofit.HttpRequests
import com.decagon.android.sq007.firstimplementation.pokemonattributes.Ability
import com.decagon.android.sq007.firstimplementation.pokemonattributes.Move
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.properties.Delegates

class PokemonDisplayActivity : AppCompatActivity(), CLickListener {

    // initialize variables
    private lateinit var binding: ActivityPokemonDisplayBinding

    private lateinit var interfaceObject: HttpRequests


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set view binding
        binding = ActivityPokemonDisplayBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

//         get xml layout
        val pokeRecyclerViewXml = binding.pokeRecyclerView

//         set layout manager
        pokeRecyclerViewXml.layoutManager = LinearLayoutManager(this)

//        pokemonRecyclerViewAdapter = pokemonListAdapter(pokeList, this)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/pokemon/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        interfaceObject = retrofit.create(HttpRequests::class.java)

        val dataCallResult: Call<PokemonData> = interfaceObject.getPokemonData()

        dataCallResult.enqueue(object : Callback<PokemonData> {
            override fun onResponse(call: Call<PokemonData>, response: Response<PokemonData>) {
                if (response.isSuccessful) {
                    pokeRecyclerViewXml.adapter = pokemonListAdapter(response.body()!!.results,
                        this@PokemonDisplayActivity,this@PokemonDisplayActivity)

                } else {
                    Log.d("Tag5", "${response.code()}")
                }
            }

            override fun onFailure(call: Call<PokemonData>, t: Throwable) {
                Log.d("Tag6", "$t")
            }
        })
    }

    override fun onItemClicked(position: Int) {

        val detailsCallResult: Call<PokemonDetails> = interfaceObject.getPokemonDetails("$position")

        detailsCallResult.enqueue(object : Callback<PokemonDetails>{
            override fun onResponse(
                call: Call<PokemonDetails>,
                response: Response<PokemonDetails>
            ) {


                if (response.isSuccessful){

                    PokemonDetailsHolder.abilities = response.body()!!.abilities
                    PokemonDetailsHolder.moves = response.body()!!.moves
                    PokemonDetailsHolder.stats = response.body()!!.stats

                    val intent:Intent = Intent()

                    intent.putExtra("URL_POSITION",position)

                    this@PokemonDisplayActivity.startActivity(Intent(this@PokemonDisplayActivity, PokemonDetailsActivity::class.java))
                }   else {
                    Log.d("Tag2","${response.code()}")
                }
            }

            override fun onFailure(call: Call<PokemonDetails>, t: Throwable) {
                Log.d("Tag3","$t")
            }
        })
    }
}