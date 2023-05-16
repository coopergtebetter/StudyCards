package com.example.studycards

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.studycards.databinding.ActivityAddCardBinding

class AddCard : AppCompatActivity() {

    private lateinit var binding: ActivityAddCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCardBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val front = binding.front
        val back = binding.back
        val submitButton = binding.button3
        val id = intent.getStringExtra("ID")

        submitButton.setOnClickListener {
            val frnt = front.text.toString()
            val bak = back.text.toString()
            if (frnt == "") {
                Toast.makeText(this, "please enter a front", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (bak == "") {
                Toast.makeText(this, "please enter a back", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val prefEdit = this.getSharedPreferences(id, Context.MODE_PRIVATE).edit()
            Log.d("AddCard", "submitting new card: $frnt, $bak")
            prefEdit.putString(frnt, bak)
            prefEdit.commit()
            val intent = Intent(this, EditCardSet::class.java)
            intent.putExtra("ID", id)
            startActivity(intent)
        }
    }
}