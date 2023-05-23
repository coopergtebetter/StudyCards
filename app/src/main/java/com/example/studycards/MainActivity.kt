package com.example.studycards

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.studycards.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        val myData = Dataset().loadData(this)
        if (myData.isEmpty()) binding.nothing.visibility = View.VISIBLE
        else binding.nothing.visibility = View.INVISIBLE
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