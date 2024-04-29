package com.technoPia.primersAuxilis.room

import androidx.room.*
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    suspend fun getUser(email:String, password: String): User?

    @Query("SELECT EXISTS(SELECT * FROM user WHERE email = :email)")
    fun codecheck(email: String): Boolean

    @Query("UPDATE user SET level = :lvl Where email = :email")
    fun levelUpdate(email: String, lvl: Int): Int
}

