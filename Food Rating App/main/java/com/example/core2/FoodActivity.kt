package com.example.core2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

class FoodActivity : AppCompatActivity() {
    private var foodId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        val food = intent.getParcelableExtra<Food>("food")

        val foodImage = findViewById<ImageView>(R.id.foodImage)
        val foodName = findViewById<TextInputEditText>(R.id.nameInput)
        val foodDate = findViewById<EditText>(R.id.dateInput)
        val foodCuisine = findViewById<TextInputEditText>(R.id.categoryInput)
        val foodRating = findViewById<RatingBar>(R.id.foodRating)

        //Populate the field with data, to make it easier for the user
        food?.apply {
            id?.let { foodId = it }
            img?.let { foodImage.setImageResource(it) }
            name?.let { foodName.setText(it) }
            date?.let { foodDate.setText(it) }
            cuisine?.let { foodCuisine.setText(it) }
            rating?.let { foodRating.rating = it }
        }
    }

    //Function that's called every time the back button is pressed.
    override fun onBackPressed() {

        //Declaration of Views
        var nameValid = true
        var cuisineValid = true
        var dateValid = true
        val foodImage = findViewById<ImageView>(R.id.foodImage)
        val foodNameLayout = findViewById<TextInputLayout>(R.id.nameInputLayout)
        val foodName = findViewById<TextInputEditText>(R.id.nameInput)
        val foodDate = findViewById<EditText>(R.id.dateInput)
        val foodCuisineLayout = findViewById<TextInputLayout>(R.id.categoryInputLayout)
        val foodCuisine = findViewById<TextInputEditText>(R.id.categoryInput)
        val foodRating = findViewById<RatingBar>(R.id.foodRating)
        val myFood = Food(foodId, foodImage?.id, foodName.text.toString(), foodDate.text.toString(), foodCuisine.text.toString(), foodRating?.rating)

        //Checking if the fields are empty
        if (foodName.text.toString() == "") {
            foodNameLayout.error = "Name cannot be empty"
            nameValid = false
        }

        if (foodCuisine.text.toString() == "") {
            foodCuisineLayout.error = "Cuisine cannot be empty"
            cuisineValid = false
        }

        if (foodDate.text.toString() == "") {
            foodDate.error = "Date cannot be empty"
            dateValid = false
        }

        // If they are not empty, sent the a Food object with the new values
        //back to the first activity with an Intent.
        if (nameValid && cuisineValid && dateValid)
        {
            val myIntent = intent
            myIntent.apply {
                putExtra("updatedFood", myFood)
            }

            setResult(RESULT_OK, myIntent)
            Log.i("S","Exiting Second Activity");
            finish()
        }
    }
}
