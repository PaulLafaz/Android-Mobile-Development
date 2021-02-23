package com.example.mytown

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class TownAdapter (private val aTownList : List<Town>): RecyclerView.Adapter<TownAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.layout_row, parent, false) as View
        return ViewHolder(view)
    }

    override fun getItemCount() : Int = aTownList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = aTownList[position]
        //Log.i("E", "ImageRes:" + item);
        holder.bind(item)
    }

    inner class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        private val town: TextView = v.findViewById(R.id.townName)
        private val timeZone: TextView = v.findViewById(R.id.timeZone)
        private val image : ImageView = v.findViewById(R.id.icon)

        fun bind(item: Town) {
            town.text = item.name
            timeZone.text = item.timeZone

            var imageName = if (item.isFavourite == 1) "ic_favorite_black_18dp" else "ic_favorite_border_black_18dp"

            val imageRes = v.context.resources.getIdentifier( imageName, "drawable", v.context.packageName)
            image.setImageResource(imageRes)

            v.setOnClickListener {
                val text = "${item.name}'s coordinates are ${item.latitude}, ${item.longitude}."
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(it.context, text, duration)
                toast.show()
            }
        }
    }
}
