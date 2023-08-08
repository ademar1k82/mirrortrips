package com.ipca.budget.travelmobile.entities

import org.json.JSONObject

class ButtonsVacType {
    var id: String? = null
    var text: String? = null

    companion object {
        fun fromJSON(jsonObject: JSONObject) : ButtonsVacType {
            return ButtonsVacType().apply {
                id = jsonObject.getString("id")
                text = jsonObject.getString("text")
            }
        }
    }
}