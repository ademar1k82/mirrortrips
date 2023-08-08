package com.ipca.budget.travelmobile

import android.content.ContentValues.TAG
import android.content.res.AssetManager
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.ipca.budget.travelmobile.entities.ButtonsVacType
import com.ipca.budget.travelmobile.entities.DataType
import com.ipca.budget.travelmobile.entities.Destiny
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream
import org.json.JSONObject

object Backend {

    val client = OkHttpClient()
    val db = Firebase.firestore

    fun fetchDestinies(scope: CoroutineScope, assets: AssetManager, dataType: DataType, callback: (ArrayList<Destiny>)->Unit)  {

        scope.launch(Dispatchers.IO) {
            db.collection("destiniesCol")
                .get()
                .addOnSuccessListener { result ->

                    for (document in result) {

                        if (dataType.main?.id == document.id) {

                            val secondRes =  document.data.get(dataType.secondary?.id)

                            val gson = Gson()

                            var result = JSONObject(gson.toJson(secondRes)).get(dataType.price?.typeStr) as JSONArray
                            val destinies = ArrayList<Destiny>()

                            if (result != null) {
                                for(index in 0 until result.length()) {
                                    destinies.add(Destiny.fromJSON(result.getJSONObject(index)))
                                }
                            }

                            scope.launch (Dispatchers.Main){
                                callback(destinies)
                            }

                            break
                        }


                    }
                }
                .addOnFailureListener { exception ->
                    throw Exception("Error on getting results")
                }
        }
    }

    fun fetchButtonsVacType(scope: CoroutineScope, mainType: String, callback: (ArrayList<ButtonsVacType>)->Unit) {


        scope.launch(Dispatchers.IO) {
            db.collection("vacTypeButtonsData")
                .get()
                .addOnSuccessListener { result ->

                    for (document in result) {

                        if (mainType == document.id) {
                            val gson = Gson()
                            var result = JSONObject(gson.toJson(document.data))

                            val buttonsVacType = ArrayList<ButtonsVacType>()

                            if (result != null) {
                                for(index in 0 until result.length()) {
                                    buttonsVacType.add(ButtonsVacType.fromJSON(result.getJSONObject(index.toString())))
                                }
                            }

                            scope.launch (Dispatchers.Main){
                                callback(buttonsVacType)
                            }

                            break
                        }


                    }
                }
                .addOnFailureListener { exception ->
                    throw Exception("Error on getting results")
                }
        }


    }

    fun fetchHotel(scope: CoroutineScope, place: String?, country: String?,
                   callback: (String) -> Unit) {


        scope.launch (Dispatchers.IO){

            val request = Request.Builder()
                .url("https://best-booking-com-hotel.p.rapidapi.com/booking/best-accommodation?cityName=$place&countryName=$country")
                .get()
                .addHeader("X-RapidAPI-Key", "fd9bda0a96mshd382468d93681e7p11744ajsn18f453e98759")
                .addHeader("X-RapidAPI-Host", "best-booking-com-hotel.p.rapidapi.com")
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return@use

                val result = response.body!!.string()
                val jsonObject = JSONObject(result)
                var url = jsonObject.getString("link")

                scope.launch(Dispatchers.Main) {
                    callback(url)
                }
            }
        }

    }
}