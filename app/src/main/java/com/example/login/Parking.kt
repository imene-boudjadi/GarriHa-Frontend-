package com.example.login

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName ="parkings")
data class Parking(
    @PrimaryKey(autoGenerate = true)
    var parkingId: Int = 0,
    var parkingName: String,
    var DescriptionParking: String
)

