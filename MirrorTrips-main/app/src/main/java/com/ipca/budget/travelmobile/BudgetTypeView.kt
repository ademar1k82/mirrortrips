package com.ipca.budget.travelmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.ipca.budget.travelmobile.entities.DataTypePrice
import com.ipca.budget.travelmobile.entities.DataTypeText

class BudgetTypeView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget_type_view)

        val containerButton = findViewById<ConstraintLayout>(R.id.container_button)

        val primaryDataType = intent.getSerializableExtra(DataTypeText.MAIN.id)
        val secondaryDataType = intent.getSerializableExtra(DataTypeText.SECONDARY.id)

        Utils.addClickEventToButtons(containerButton) {
            val intent = Intent(this@BudgetTypeView, DestinyChoiceView::class.java)

            intent.putExtra(DataTypeText.MAIN.id, primaryDataType)
            intent.putExtra(DataTypeText.SECONDARY.id, secondaryDataType)

            when(it) {
                "lowCostBtn" -> intent.putExtra(DataTypeText.PRICE.id, DataTypePrice.LOW_COST)
                "averageBtn" -> intent.putExtra(DataTypeText.PRICE.id, DataTypePrice.AVERAGE)
                "noBudgetBtn" -> intent.putExtra(DataTypeText.PRICE.id, DataTypePrice.NO_BUDGET)
            }

            startActivity(intent)
        }
    }
}