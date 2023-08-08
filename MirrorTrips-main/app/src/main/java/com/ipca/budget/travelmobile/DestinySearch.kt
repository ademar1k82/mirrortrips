package com.ipca.budget.travelmobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ipca.budget.travelmobile.Utils.addClickEventToButtons
import com.ipca.budget.travelmobile.entities.DataTypePrimary
import com.ipca.budget.travelmobile.entities.DataTypeText


class DestinySearch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destiny_search)

        val containerButton = findViewById<ConstraintLayout>(R.id.container_button)

        addClickEventToButtons(containerButton) {
            val intent = Intent(this@DestinySearch, VacationTypeView::class.java)

            when(it) {
                "beachBtn" -> intent.putExtra(DataTypeText.MAIN.id, DataTypePrimary.BEACH)
                "snowBtn" -> intent.putExtra(DataTypeText.MAIN.id, DataTypePrimary.SNOW)
                "cityBreakBtn" -> intent.putExtra(DataTypeText.MAIN.id, DataTypePrimary.CITY_BREAK)
                "natureBtn" -> intent.putExtra(DataTypeText.MAIN.id, DataTypePrimary.NATURE)
            }

            startActivity(intent)
        }
    }

}