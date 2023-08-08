package com.ipca.budget.travelmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.lifecycleScope
import com.ipca.budget.travelmobile.Backend.fetchDestinies
import com.ipca.budget.travelmobile.entities.*

class DestinyChoiceView : AppCompatActivity() {

    var dataType = DataType()
    var destinies = ArrayList<Destiny>()
    var adapter = DestinyChoiceAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destiny_choice_view)

        val listViewDestinies = findViewById<ListView>(R.id.listViewDestinies);
        listViewDestinies.adapter = adapter

        dataType.main = intent.getSerializableExtra(DataTypeText.MAIN.id) as DataTypePrimary
        dataType.secondary = intent.getSerializableExtra(DataTypeText.SECONDARY.id) as DataTypeSecondary
        dataType.price = intent.getSerializableExtra(DataTypeText.PRICE.id) as DataTypePrice

        fetchDestinies(lifecycleScope, assets, dataType) {
            destinies = it
            adapter.notifyDataSetChanged()
        }

        print(destinies)
    }

    inner class DestinyChoiceAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return destinies.size;
        }

        override fun getItem(p0: Int): Any {
            return destinies[p0]
        }

        override fun getItemId(p0: Int): Long {
            return 0L
        }

        override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
            val rootView = layoutInflater.inflate(R.layout.activity_button_view,parent,false)

            val dinamicButton = rootView.findViewById<Button>(R.id.dynamicButton)

            dinamicButton.text = destinies[position].name

            dinamicButton.setOnClickListener {
                val intent = Intent(this@DestinyChoiceView, VacationsPeriodChoice::class.java)
                intent.putExtra(DataTypeText.DESTINY.id, destinies[position].toJSON().toString())
                startActivity(intent)
            }

            return rootView
        }

    }
}