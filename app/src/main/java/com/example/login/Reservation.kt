package com.example.login

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName ="reservations", foreignKeys = [ForeignKey(entity = Parking::class, parentColumns = ["parkingId"], childColumns = ["idparking"], onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE), ForeignKey(entity = User::class, parentColumns = ["userId"], childColumns = ["iduser"], onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE)])
data class Reservation(
    @PrimaryKey(autoGenerate = true)
    var reservationId: Int = 0,
//    var parkingName: String,
    var DescriptionReservation: String,
    var iduser : Int,
    var idparking : Int
)
