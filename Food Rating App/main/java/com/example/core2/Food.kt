package com.example.core2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Food(val id : Int?,
                val img : Int?,
                var name : String?,
                var date : String?,
                var cuisine : String?,
                var rating : Float?
                ) : Parcelable {
    }

