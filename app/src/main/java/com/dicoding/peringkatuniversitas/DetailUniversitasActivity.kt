package com.dicoding.peringkatuniversitas

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailUniversitasActivity : AppCompatActivity() {
    private lateinit var universitas: Universitas
    private lateinit var tvAdditionalDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_universitas)

        val imgLogo: ImageView = findViewById(R.id.img_logo)
        val tvName: TextView = findViewById(R.id.tv_name)
        val tvDescription: TextView = findViewById(R.id.tv_description)
        tvAdditionalDescription = findViewById(R.id.tv_additional_description)

        universitas = intent.getParcelableExtra("universitas") ?: return

        imgLogo.setImageResource(universitas.photo)
        tvName.text = universitas.name
        tvDescription.text = universitas.description
        tvAdditionalDescription.text = universitas.additionalDescription
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                shareUniversitas()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareUniversitas() {
        val shareText = "Check out this university: ${universitas.name}\n${universitas.description}"

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }

        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }
}