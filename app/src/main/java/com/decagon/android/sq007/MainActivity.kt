package com.decagon.android.sq007

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.decagon.android.sq007.databinding.ActivityMainBinding
import com.decagon.android.sq007.firstimplementation.PokemonDisplayActivity
import com.decagon.android.sq007.secondimplementation.AccessPhoneStorageActivity

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))

        setContentView(binding.root)

        binding.btnFirstImplementation.setOnClickListener(){
            startActivity(Intent(this, PokemonDisplayActivity::class.java))
        }

        binding.btnSecondImplementation.setOnClickListener(){
            startActivity(Intent(this, AccessPhoneStorageActivity::class.java))
        }
    }

}
