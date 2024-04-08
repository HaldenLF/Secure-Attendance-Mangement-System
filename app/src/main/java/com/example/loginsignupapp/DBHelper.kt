package com.example.loginsignupapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.security.KeyStore.PasswordProtection

class DBHelper (context: Context): SQLiteOpenHelper(context, DBName, null, 1){
    companion object{
        const val DBName = "Login.db"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create Table users(username TEXT primary key, password TEXT, userEmail TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop Table if exists users")
        onCreate(db)
    }

    fun insertData(username: String, password: String, userEmail: String): Boolean {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put("username", username)
            put("password", password)
            put("userEmail", userEmail)
        }
        val result = db.insert("users", null, contentValues)
        return result != -1L
    }

    fun checkUsername(username: String): Boolean {
        val db = writableDatabase
        val cursor = db.rawQuery("Select * from users where username = ?", arrayOf(username))
        return cursor.count > 0
    }

    fun checkUsernamePassword(username: String, password: String, userEmail: String): Boolean {
        val db = writableDatabase
        val cursor = db.rawQuery("Select * from users where username = ? and password = ? and userEmail = ?", arrayOf(username, password, userEmail))
        return cursor.count > 0
    }
}