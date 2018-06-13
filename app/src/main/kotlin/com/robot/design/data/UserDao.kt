package com.robot.design.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Single


@Dao
interface UserDao {
    @Query("select * from user where user_id = :userId")
    fun getUser(userId: Int): Single<User>

    @Query("select * from user")
    fun getUsers(): Single<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("delete from user")
    fun deleteAllUsers()
}