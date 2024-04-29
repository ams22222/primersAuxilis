package com.technoPia.primersAuxilis.room

import android.app.Application
import androidx.room.Room

class App: Application() {
    companion object {
        lateinit var db: DataBase
    }


    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
                this,
                DataBase::class.java,
                "room-db"
            ).build()
    }
}