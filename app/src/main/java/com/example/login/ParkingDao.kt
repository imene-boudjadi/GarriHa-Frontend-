package com.example.login

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ParkingDao {
    @Query("SELECT * FROM parkings")
    fun getAllParkings(): List<Parking>
}
