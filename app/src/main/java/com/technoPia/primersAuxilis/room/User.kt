package com.technoPia.primersAuxilis.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = false)
    var email: String,
    var username: String,
    var password: String,
    var level: Int
): java.io.Serializable