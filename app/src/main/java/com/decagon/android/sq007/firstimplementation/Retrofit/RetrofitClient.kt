//package com.decagon.android.sq007.firstimplementation.Retrofit
//
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//object RetrofitClient {
//
//    private var retrofitInstance: Retrofit? = null
//
//    val retrofit: Retrofit
//        get() {
//            if (retrofit == null){
//                // create an instance of retrofit
//                val retrofit: Retrofit = Retrofit.Builder()
//                    .baseUrl("https://pokeapi.co/api/v2/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//            }
//
//            return retrofit
//        }
//
//
//}