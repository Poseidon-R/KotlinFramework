package com.robot.design.data
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context


@Database(entities = arrayOf(User::class), version = 1)
@TypeConverters(UserConvert::class)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao//定义dao对象，多个时写法类似

    companion object {
        @Volatile private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        /**
         * 创建数据库
         */
        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext, UserDatabase::class.java, "User.db").build()
    }

    override fun clearAllTables() {

    }
}