package com.dicoding.peringkatuniversitas

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val imgProfile: ImageView = findViewById(R.id.img_profile)
        Glide.with(this)
            .load(R.drawable.fotosaya)
            .circleCrop()
            .into(imgProfile)
    }
}