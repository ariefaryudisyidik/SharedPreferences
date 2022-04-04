package com.binar.ariefaryudisyidik.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.binar.ariefaryudisyidik.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        binding.btnSave.setOnClickListener {
            val id: Int = Integer.parseInt(binding.etInputId.text.toString())
            val name: String = binding.etInputName.text.toString()
            sharedPreferences
                .edit()
                .putInt("id_key", id)
                .putString("name_key", name)
                .apply()
            Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
        }

        binding.btnView.setOnClickListener {
            val sharedIdValue = sharedPreferences.getInt("id_key", 0)
            val sharedNameValue = sharedPreferences.getString("name_key", "defaultname")
            if (sharedIdValue == 0 && sharedNameValue.equals("defaultname")) {
                binding.tvShowName.text = "default name: $sharedNameValue"
                binding.tvShowId.text = "default id: $sharedIdValue"
                Toast.makeText(this, "Data View Kosong", Toast.LENGTH_SHORT).show()
            } else {
                binding.tvShowName.text = sharedNameValue
                binding.tvShowId.text = sharedIdValue.toString()
                Toast.makeText(this, "Data view Ditampilkan", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnClear.setOnClickListener {
            sharedPreferences.edit().clear().apply()
            binding.tvShowName.text = null
            binding.tvShowId.text = null
            Toast.makeText(this, "Data Clear", Toast.LENGTH_SHORT).show()
        }
    }
}