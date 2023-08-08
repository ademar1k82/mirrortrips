package com.ipca.budget.travelmobile.entities

import org.json.JSONObject

class Destiny {
    var name: String? = null
    var country: String? = null
    var image: String? = null
    var apiTime: String? = null
    var description: String? = null
    var tourismLink: String? = null
    var youtubeLink: String? = null
    var airport: String? = null
    var cityHotel: String? = null

    fun toJSON() : JSONObject{
        val jsonObject = JSONObject()
        jsonObject.put("name", name)
        jsonObject.put("country", country)
        jsonObject.put("image", image)
        jsonObject.put("apiTime"    , apiTime)
        jsonObject.put("description" , description)
        jsonObject.put("tourismLink" , tourismLink)
        jsonObject.put("youtubeLink" , youtubeLink)
        jsonObject.put("airport" , airport)

        return jsonObject
    }

    companion object {
        fun fromJSON(jsonObject: JSONObject) : Destiny {
            return Destiny().apply {
                name = jsonObject.getString("name")
                country = jsonObject.getString("country")
                image = jsonObject.getString("image")
                apiTime = jsonObject.getString("apiTime")
                description = jsonObject.getString("description")
                tourismLink = jsonObject.getString("tourismLink")
                youtubeLink = jsonObject.getString("youtubeLink")
                airport = jsonObject.getString("airport")
            }
        }
    }
}