package com.decagon.android.sq007.firstimplementation

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.ViewTarget
import com.decagon.android.sq007.R.*
import com.decagon.android.sq007.firstimplementation.pokemondataentry.PokemonDataResult

class pokemonListAdapter(var recyclerList: List<PokemonDataResult>, var context: Context,var clickListener:CLickListener): RecyclerView.Adapter<pokemonListAdapter.PokemonModelViewHolder>() {


        // private val pokemonAvatar: ImageView = findViewById(R.id.poke_avatar)

        // inner class recycler view view holder takes the view type to be shown, and places it in the view holder
        inner class PokemonModelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

            private val pokemonName: TextView = itemView.findViewById(id.poke_name)
            private val pokemonImage: ImageView = itemView.findViewById(id.poke_avatar)


         fun bind(item: List<PokemonDataResult>, position: Int){
              pokemonName.text = item[position].name
                Glide.with(context)
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${position + 1}.png")
                    .into(pokemonImage)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonModelViewHolder {

            // creates the viewHolders that holds the data to be displayed
            val recyclerLayout = LayoutInflater.from(parent.context)
                .inflate(layout.pokemon_list_display_model, parent, false)


            // returns a view holder with the view to be shown inside
            return PokemonModelViewHolder(recyclerLayout)

        }

        override fun onBindViewHolder(holder: PokemonModelViewHolder, position: Int) {


                // bind data to each recycler view
                    holder.bind(recyclerList, position)

    //             set on click listener to each view
            holder.itemView.setOnClickListener {
                clickListener.onItemClicked(position)

                }
            }


        // gets the count of data to be used for binding
        override fun getItemCount(): Int {
            return recyclerList.size
        }

//        fun getList(myList: ArrayList<PokemonDetails>) {
//            this.recyclerList = myList
//            notifyDataSetChanged()
//        }
    }
