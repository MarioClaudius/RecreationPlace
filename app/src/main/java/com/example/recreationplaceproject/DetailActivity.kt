package com.example.recreationplaceproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_DESCRIPTION = "extra_description"
        const val EXTRA_IMAGE = "extra_image"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val tvNameReceived : TextView = findViewById(R.id.tv_nama_tempat_received)
        val imageReceived : ImageView = findViewById(R.id.gambar_tempat_received)
        val tvDescriptionReceived : TextView = findViewById(R.id.tv_deskripsi_received)

        val name = intent.getStringExtra(EXTRA_NAME)
        val image = intent.getIntExtra(EXTRA_IMAGE,0)
        val description = intent.getStringExtra(EXTRA_DESCRIPTION)

        imageReceived.setImageResource(image)
        tvNameReceived.text = name
        tvDescriptionReceived.text = description

        actionBar?.title = name
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }
}