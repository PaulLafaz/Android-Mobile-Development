package com.example.noisesoff

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    private var health: Int = 10
    private var colour = 1

    //Logging functions
    override fun onStart() {
        super.onStart()
        Log.i("LIFECYCLE", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("LIFECYCLE", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("LIFECYCLE", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("LIFECYCLE", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LIFECYCLE", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("LIFECYCLE", "onRestart")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("LIFECYCLE", "onCreate")

        var score = findViewById<TextView>(R.id.score)
        var sneeze = findViewById<Button>(R.id.sneeze_button)
        var meds = findViewById<Button>(R.id.medication_button)
        var blow = findViewById<Button>(R.id.blow_button)
        var player = MediaPlayer.create(this, R.raw.sneezing_sound)

        //Load any saved saved data we stored using the savedInstanceState function
        savedInstanceState?.let {
            health = it.getInt("SCORE")
            colour = it.getInt("COLOUR")
            score.text = health.toString()
            decodeColours()
        }

        //Things to do when the the app is first created
        score.text = health.toString()
        determineColour()
        decodeColours()

        //Events that happen when we click the sneeze button
        sneeze.setOnClickListener {
            sneezeButtonPressed()
            score.text = health.toString()
            determineColour()
            decodeColours()
        }

        //Events that happen when we click the medication button
        meds.setOnClickListener {
            medButtonPressed()
            score.text = health.toString()
            determineColour()
            decodeColours()
        }

        //Events that happen when we click the blow nose button
        blow.setOnClickListener {
            player.start()
        }
    }

    //Function that determines the action when the user clicks the med button
    private fun medButtonPressed() {
        when(health) {
            in 0..8  -> health += 2
            9 -> health += 1
        }
    }

    //Function that determines the action when the user clicks the sneeze button
    private fun sneezeButtonPressed() {
        if(health > 0) {
            health -= 1
        }
    }

    //Function that paints the text colour of the score depending on the value of colour
    private fun decodeColours() {
        when(colour)
        {
            1 -> score.setTextColor(Color.RED)
            2 ->  score.setTextColor(Color.BLUE)
            3 -> score.setTextColor(Color.GRAY)
        }
    }

    //Function that assigns a number to the value "colour" depending on the range of the score
    private fun determineColour() {
        when(health) {
            in 0..5 -> colour = 1
            in 6..7 -> colour = 2
            in 8..10 -> colour = 3
        }
    }

    //Saving health and colour method
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("SCORE", health)
        outState.putInt("COLOUR", colour)
        super.onSaveInstanceState(outState)
    }
}

