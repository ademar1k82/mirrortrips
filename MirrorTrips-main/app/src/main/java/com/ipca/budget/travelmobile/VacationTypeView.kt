package com.ipca.budget.travelmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.ipca.budget.travelmobile.Backend.fetchButtonsVacType
import com.ipca.budget.travelmobile.entities.*
import java.io.Serializable

class VacationTypeView : AppCompatActivity() {

    var adapter = VacationsTypeViewAdapter()
    var buttons = ArrayList<ButtonsVacType>()
    var primaryDataType: Serializable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vacation_type)

        primaryDataType = intent.getSerializableExtra(DataTypeText.MAIN.id)
        fetchButtonsVacType(lifecycleScope, (primaryDataType as DataTypePrimary).id) {
            buttons = it
            adapter.notifyDataSetChanged()
        }

        val listViewDestinies = findViewById<ListView>(R.id.vacTypeListView);
        listViewDestinies.adapter = adapter

    }

    inner class VacationsTypeViewAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return buttons.size
        }

        override fun getItem(p0: Int): Any {
            return buttons[p0]
        }

        override fun getItemId(p0: Int): Long {
            return 0L
        }

        override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
            val rootView = layoutInflater.inflate(R.layout.activity_button_view,parent,false)

            val dinamicButton = rootView.findViewById<Button>(R.id.dynamicButton)

            dinamicButton.text = buttons[position].text

            dinamicButton.setOnClickListener {
                val intent = Intent(this@VacationTypeView, BudgetTypeView::class.java)
                intent.putExtra(DataTypeText.MAIN.id, primaryDataType)
                intent.putExtra(DataTypeText.SECONDARY.id, DataTypeSecondary.getDataById(buttons[position].id))

                startActivity(intent)
            }

            return rootView
        }

    }
}