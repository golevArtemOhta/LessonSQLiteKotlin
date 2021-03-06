package com.example.lessonsqlitekotlin.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class MyDBManager(context: Context) {
    val myDBHelper = MyDBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb(){
        db = myDBHelper.writableDatabase
    }

    fun insertToDb(title: String, content: String){
        val values =  ContentValues().apply {
            put(MyDBNameClass.COLUMN_NAME_TITLE, title)
            put(MyDBNameClass.COLUMN_NAME_CONTENT, content)
        }
        db?.insert(MyDBNameClass.TABLE_NAME, null, values)
    }
    fun readDBData(): ArrayList<String>{
        val dataList = ArrayList<String>()
        val cursor = db?.query(MyDBNameClass.TABLE_NAME, null, null,
            null, null, null, null)

        with(cursor){
            while (this?.moveToNext()!!){
                val dataText = cursor?.getString(cursor.getColumnIndex(MyDBNameClass.COLUMN_NAME_TITLE))
                dataList.add(dataText.toString())
            }
        }
        cursor?.close()
        return dataList
    }

    fun closeDB(){
        myDBHelper.close()
    }
}











