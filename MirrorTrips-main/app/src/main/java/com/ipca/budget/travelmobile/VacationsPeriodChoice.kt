package com.ipca.budget.travelmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.ipca.budget.travelmobile.Backend.fetchHotel
import com.ipca.budget.travelmobile.Utils.addWebPage
import com.ipca.budget.travelmobile.entities.DataTypeText
import com.ipca.budget.travelmobile.entities.Destiny
import okhttp3.*
import org.json.JSONObject

class VacationsPeriodChoice : AppCompatActivity() {

    val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vacations_period_choice)

        val destiny = Destiny.fromJSON(JSONObject(intent.getStringExtra(DataTypeText.DESTINY.id)))

        val containerButton = findViewById<ConstraintLayout>(R.id.container_button)
        val continueBtn = findViewById<Button>(R.id.vacPeriodContinueBtn)
        val youtubeBtn = findViewById<Button>(R.id.youtubeBtn)
        val tourismBtn = findViewById<Button>(R.id.tourismLink)
        val bookingBtn = findViewById<Button>(R.id.bookingBtn)
        val googleFlightsBtn = findViewById<Button>(R.id.googleFlightBtn)

        addWebPage(this@VacationsPeriodChoice, youtubeBtn, destiny.youtubeLink)
        addWebPage(this@VacationsPeriodChoice, tourismBtn, destiny.tourismLink)
        addWebPage(this@VacationsPeriodChoice, googleFlightsBtn, "https://www.google.com/travel/flights")

        fetchHotel(lifecycleScope, destiny.name, destiny.country, {
            addWebPage(this@VacationsPeriodChoice, bookingBtn, it)
        })


        continueBtn.setOnClickListener {
            val intent = Intent(this@VacationsPeriodChoice, EmailSendView::class.java)
            startActivity(intent)
        }
    }
}