package com.decagon.android.sq007.firstimplementation.Retrofit

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.decagon.android.sq007.R
import com.decagon.android.sq007.firstimplementation.pokemonattributes.Ability

class WeaknessRecyclerViewAdapter(var WeaknessList: List<Ability>) : RecyclerView.Adapter<WeaknessRecyclerViewAdapter.PokemonDetailsViewHolder>(){


    inner class PokemonDetailsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val weaknessHolder: RecyclerView = itemView.findViewById(R.id.weakness_recycler_view)

        fun binder(item: Ability) {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonDetailsViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: PokemonDetailsViewHolder, position: Int) {
        binder(item)
    }

    override fun getItemCount(): Int {
        return WeaknessList.size
    }
}