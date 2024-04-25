package com.example.login

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.login.Parking
import com.example.login.ParkingDao
import com.example.login.Reservation
import com.example.login.ReservationDao
import com.example.login.User
import com.example.login.UserDao

@Database(entities = [User::class, Parking::class, Reservation::class], version = 2)
abstract class UserDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getParkingDao(): ParkingDao
    abstract fun getReservationDao(): ReservationDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    UserDatabase::class.java,
                    "users_database2"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
