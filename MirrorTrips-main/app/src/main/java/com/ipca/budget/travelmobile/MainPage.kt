package com.ipca.budget.travelmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ipca.budget.travelmobile.entities.*

class MainPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        var dataType = DataType()
        var destinies = ArrayList<Destiny>();

        dataType.main = DataTypePrimary.BEACH
        dataType.secondary = DataTypeSecondary.CONFORT
        dataType.price = DataTypePrice.LOW_COST

        var startBtn = findViewById<Button>(R.id.startBtn)

        startBtn.setOnClickListener {
            val intent = Intent(this@MainPage, DestinySearch::class.java)
            startActivity(intent)
        }
    }
}