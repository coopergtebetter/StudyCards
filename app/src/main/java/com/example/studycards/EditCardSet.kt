package com.example.studycards

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.studycards.databinding.ActivityEditCardSetBinding
import com.example.studycards.databinding.ActivityMainBinding

class EditCardSet : AppCompatActivity() {

    private lateinit var binding: ActivityEditCardSetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditCardSetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getStringExtra("ID")
        val myData = Dataset().loadCards(this, id!!)
        if (myData.isEmpty()) binding.nothing.visibility = View.VISIBLE
        else binding.nothing.visibility = View.INVISIBLE
        val recycler = binding.cards
        recycler.adapter = CardAdapter(this, myData)
        recycler.setHasFixedSize(true)
        val fab = binding.newCards
        fab.setOnClickListener {
            val intent = Intent(this, AddCard::class.java)
            intent.putExtra("ID", id)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        binding = ActivityEditCardSetBinding.inflate(layoutInflater)
        val id = intent.getStringExtra("ID")
        val myData = Dataset().loadCards(this, id!!)
        if (myData.isEmpty()) binding.nothing.visibility = View.VISIBLE
        else binding.nothing.visibility = View.INVISIBLE
        val recycler = binding.cards
        recycler.adapter = CardAdapter(this, myData)
        recycler.setHasFixedSize(true)

    }
}