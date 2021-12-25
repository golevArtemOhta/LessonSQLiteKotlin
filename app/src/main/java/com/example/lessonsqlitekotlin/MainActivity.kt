package com.example.lessonsqlitekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.lessonsqlitekotlin.db.MyDBManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val myDBManaager = MyDBManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()
        myDBManaager.openDb()
        val dataList = myDBManaager.readDBData()
        for (item in dataList){
            tvTest.append(item)
            tvTest.append("\n")

        }
    }

    fun onClickSave(view: View) {
        tvTest.text = ""

        myDBManaager.insertToDb(edTitle.text.toString(), edContent.text.toString())
        val dataList = myDBManaager.readDBData()
        for (item in dataList){
            tvTest.append(item)
            tvTest.append("\n")

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        myDBManaager.closeDB()
    }
}