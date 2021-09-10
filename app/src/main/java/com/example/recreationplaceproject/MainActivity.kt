package com.example.recreationplaceproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(){
    private lateinit var rvTempat: RecyclerView
    private var list: ArrayList<TempatRekreasi> = arrayListOf()
    private var title: String = "List Tempat Rekreasi"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvTempat = findViewById(R.id.rv_recreation_place)
        rvTempat.setHasFixedSize(true)

        list.addAll(DataTempatRekreasi.listData)
        supportActionBar?.title = "Daftar Tempat Rekreasi"
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setActivityMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setActivityMode(selectMode : Int){
        when(selectMode){
            R.id.menu_about->{
                val aboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(aboutIntent)
            }
        }
    }

    private fun showRecyclerList() {
        rvTempat.layoutManager = LinearLayoutManager(this)
        val listAdapterTempat = ListAdapterTempat(list)
        rvTempat.adapter = listAdapterTempat
    }


}