package com.example.mobileprogrammingteam5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileprogrammingteam5.databinding.ActivityDogamBinding
import com.google.android.material.tabs.TabLayoutMediator

class Dogam : AppCompatActivity() {
    var textarr = arrayListOf<String>("땅", "강", "하늘")
    var iconarr = arrayListOf<Int>(R.drawable.ground, R.drawable.turtle, R.drawable.bird0)
    val data : ArrayList<AnimalData> = ArrayList()
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: AnimalAdapter

    lateinit var binding: ActivityDogamBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDogamBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()
    }

    private fun initLayout() {
        binding.viewpager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewpager){
                tab, position ->
            tab.text = textarr[position]
            tab.setIcon(iconarr[position])
        }.attach()
    }
}