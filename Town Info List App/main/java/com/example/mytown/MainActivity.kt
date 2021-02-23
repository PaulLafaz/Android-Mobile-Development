package com.example.mytown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val townList = mutableListOf<Town>()

        resources.openRawResource(R.raw.au_locations).bufferedReader()
            .forEachLine {
                val temp = it.split(",")
                townList.add(Town(temp[0], temp[1], temp[2], temp[3], temp[4].toInt()))
            }

        val townRecycleList = findViewById<RecyclerView>(R.id.townList)

        townRecycleList.adapter = TownAdapter(townList)
        townRecycleList.layoutManager = LinearLayoutManager(this)
    }
}