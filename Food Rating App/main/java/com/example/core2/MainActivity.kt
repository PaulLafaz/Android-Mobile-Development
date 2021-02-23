package com.example.core2

import android.R.attr
import android.R.attr.data
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private val listOfFood = mutableListOf<Food>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Declaration of Views
        val imageButterChicken = findViewById<ImageView>(R.id.imageButterChicken)
        val imageIceCream = findViewById<ImageView>(R.id.imageIceCream)
        val imageGnocchi = findViewById<ImageView>(R.id.imageGnocchi)
        val imageWrap = findViewById<ImageView>(R.id.imageWrap)
        val ratingBarButterChicken = findViewById<RatingBar>(R.id.ratingBarButterChick)
        val ratingBarIceCream = findViewById<RatingBar>(R.id.ratingBarIcecream)
        val ratingBarGnocchi = findViewById<RatingBar>(R.id.ratingBarGnocchi)
        val ratingBarWrap = findViewById<RatingBar>(R.id.ratingBarWrap)

        //Butter Chicken's on Click Listener
        imageButterChicken.setOnClickListener {
            val name = getString(R.string.butter_chicken)
            val date = "05/03/2020"
            val cuisine = "Indian"
            val ratingScore = ratingBarButterChicken.rating
            val imageId = R.mipmap.ic_butter_chicken
            var butterChicken = Food(1, imageId, name, date, cuisine, ratingScore)

            dataInitialisation(butterChicken)
        }

        //Ice Cream's on Click Listener
        imageIceCream.setOnClickListener {
            val name = getString(R.string.ice_cream)
            val date = "22/10/2019"
            val cuisine = "Dessert"
            val ratingScore = ratingBarIceCream.rating
            val imageId = R.mipmap.ic_cookie_dough_icecream
            var iceCream = Food(2, imageId, name, date, cuisine, ratingScore)

            //Populating Food Object and Intent
            dataInitialisation(iceCream)
        }

        //Gnocchi's on Click Listener
        imageGnocchi.setOnClickListener {
            val name = getString(R.string.gnocchi)
            val date = "16/01/2018"
            val cuisine = "Italian"
            val ratingScore = ratingBarGnocchi.rating
            val imageId = R.mipmap.ic_gnocchi
            var gnocchi = Food(3, imageId, name, date, cuisine, ratingScore)

            dataInitialisation(gnocchi)
        }

        //Wrap's on Click Listener
        imageWrap.setOnClickListener {
            val name = getString(R.string.wrap)
            val date = "31/12/2010"
            val cuisine = "Greek"
            val ratingScore = ratingBarWrap.rating
            val imageId = R.mipmap.ic_souvlaki_wrap
            var wrap = Food(4, imageId, name, date, cuisine, ratingScore)

            dataInitialisation(wrap)
        }
    }

    //Function Initalising Intent and a Food Object
    fun dataInitialisation( initialisedFood : Food )
    {
        var myFood = initialisedFood

        for (aFood in listOfFood) {
            if (aFood.id == initialisedFood.id ) {
                myFood = Food(aFood.id,
                              initialisedFood.img,
                              aFood.name,
                              aFood.date,
                              aFood.cuisine,
                              initialisedFood.rating)
            }
        }

        val myIntent = Intent(this, FoodActivity::class.java).apply {
            putExtra("food", myFood)
        }
        startActivityForResult(myIntent, 1);
    }

    //Funtion used to determine which field in the 1st activity will be updated
    fun updateFoodDetails( updatedFood : Food? )
    {
        val ratingBarButterChicken = findViewById<RatingBar>(R.id.ratingBarButterChick)
        val ratingBarIceCream = findViewById<RatingBar>(R.id.ratingBarIcecream)
        val ratingBarGnocchi = findViewById<RatingBar>(R.id.ratingBarGnocchi)
        val ratingBarWrap = findViewById<RatingBar>(R.id.ratingBarWrap)

        val titleButterChicken = findViewById<TextView>(R.id.titleButterChicken)
        val titleIceCream = findViewById<TextView>(R.id.titleIceCream)
        val titleGnocchi = findViewById<TextView>(R.id.titleGnocchi)
        val titleWrap = findViewById<TextView>(R.id.tittleWrap)

        when(updatedFood?.id){

            1 -> {
                titleButterChicken.text = updatedFood?.name.toString()
                updatedFood?.rating?.let { ratingBarButterChicken?.rating = it }
            }

            2 -> {
                titleIceCream.text = updatedFood?.name.toString()
                updatedFood?.rating?.let { ratingBarIceCream?.rating = it }
            }

            3 -> {
                titleGnocchi.text = updatedFood?.name.toString()
                updatedFood?.rating?.let { ratingBarGnocchi?.rating = it }
            }

            4 -> {
                titleWrap.text = updatedFood?.name.toString()
                updatedFood?.rating?.let { ratingBarWrap?.rating = it }
            }
        }
    }


    // On Activity Result Function that's used every time the user a result is sent back from
    //the second activity. meaning when the user updates the food details.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val updatedFood = data?.getParcelableExtra<Food>("updatedFood")
        var exists = 0

        //If the food exist int he list, update the entry
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                for (aFood in listOfFood) {
                    if (aFood.id == updatedFood?.id) {
                        aFood.name = updatedFood?.name
                        aFood.date = updatedFood?.date
                        aFood.cuisine = updatedFood?.cuisine
                        aFood.rating = updatedFood?.rating
                        exists = 1
                        break
                    }
                }

                //Function call
                updateFoodDetails(updatedFood)

                //If it doesnt exist in the list, add it to the list
                if (exists == 0)
                {
                    updatedFood?.let { listOfFood.add(it) }
                }
            }

            Log.i("H", "RequestCode:" + requestCode);
            Log.i("H", "ResultCode:" + resultCode )
            Log.i("H", "Updated Food:" + updatedFood )
        }
    }

}