package com.dicoding.peringkatuniversitas

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvUniversitast: RecyclerView
    private val list = ArrayList<Universitas>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUniversitast = findViewById(R.id.rv_universitast)
        rvUniversitast.setHasFixedSize(true)
        list.addAll(getListUniversitast())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvUniversitast.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvUniversitast.layoutManager = GridLayoutManager(this, 2)
            }
            R.id.action_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListUniversitast(): ArrayList<Universitas> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataAdditionalDescription = resources.getStringArray(R.array.data_additional_description)
        val listUniversitas = ArrayList<Universitas>()

        val size = minOf(dataName.size, dataDescription.size, dataPhoto.length(), dataAdditionalDescription.size)

        for (i in 0 until size) {
            val universitas = Universitas(
                dataName[i],
                dataDescription[i],
                dataPhoto.getResourceId(i, -1),
                dataAdditionalDescription[i]
            )
            listUniversitas.add(universitas)
        }

        dataPhoto.recycle()
        return listUniversitas
    }

    private fun showRecyclerList() {
        rvUniversitast.layoutManager = LinearLayoutManager(this)
        val listUniversitasAdapter = ListUniversitasAdapter(list)
        rvUniversitast.adapter = listUniversitasAdapter

        listUniversitasAdapter.setOnItemClickCallback(object : ListUniversitasAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Universitas) {
                showSelectedUniversitas(data)
            }
        })
    }

    private fun showSelectedUniversitas(universitas: Universitas) {
        val intent = Intent(this, DetailUniversitasActivity::class.java)
        intent.putExtra("universitas", universitas)
        startActivity(intent)
    }
}