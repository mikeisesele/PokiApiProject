package com.decagon.android.sq007.firstimplementation.Retrofit

import com.decagon.android.sq007.firstimplementation.PokemonData
import com.decagon.android.sq007.firstimplementation.PokemonDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HttpRequests {

//    // function to make call
//    fun fetchPokemonResponse(@Query("") limit: Int, @Query("") offset: Int): Call<PokemonData>


    // get specific url of each pokemon
    @GET("{url}")
   fun getPokemonDetails(@Path("url") url:String) : Call<PokemonDetails>


//    // get specific url of each pokemon
    @GET("?offset=0&limit=500")
    fun getPokemonData() : Call<PokemonData>


//    @GET
//    fun getAllPokemonDataList(s: String): Call<Pokemon>


// jumping cloud
//    var Arraylength = c.size
//    var count = -1
//    var Position = 0
//
//    while(Position < Arraylength){
//        if(Position + 2 < Arraylength && c[Position + 2] == 0){
//            Position++
//        }
//        Position++
//        count++
//    }
//
//    return count

}