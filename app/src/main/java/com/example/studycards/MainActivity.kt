package com.example.studycards

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studycards.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myData = Dataset().loadData(this)
        val recycler = binding.recycler
        recycler.adapter = CardSetAdapter(this, myData)
        recycler.setHasFixedSize(true)
        val fab = binding.newCard
        fab.setOnClickListener {
            val intent = Intent(this, CreateNewCardSet::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        val myData = Dataset().loadData(this)
        val recycler = binding.recycler
        recycler.adapter = CardSetAdapter(this, myData)
        recycler.setHasFixedSize(true)
        val fab = binding.newCard
        fab.setOnClickListener {
            val intent = Intent(this, CreateNewCardSet::class.java)
            startActivity(intent)
        }
    }
}