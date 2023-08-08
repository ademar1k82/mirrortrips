package com.ipca.budget.travelmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.ipca.budget.travelmobile.Utils.getEmailPostBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject

class EmailSendView : AppCompatActivity() {

    val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_send_view)

        val containerButton = findViewById<ConstraintLayout>(R.id.container_button)

        var emailComp = findViewById<EditText>(R.id.EmailNameEdit)
        var subjectComp = findViewById<EditText>(R.id.EmailSubjectEdit)
        var emailBodyComp = findViewById<EditText>(R.id.EmailMessageEdit)

        Utils.addClickEventToButtons(containerButton) {


            lifecycleScope.launch(Dispatchers.IO) {
                val mediaType = "application/json".toMediaTypeOrNull()
                val body = RequestBody.create(mediaType,
                    getEmailPostBody(emailComp.text.toString(), subjectComp.text.toString(), emailBodyComp.text.toString())
                )

                val request = Request.Builder()
                    .url("https://http2smtp.p.rapidapi.com/send")
                    .post(body)
                    .addHeader("content-type", "application/json")
                    .addHeader("X-RapidAPI-Key", "fd9bda0a96mshd382468d93681e7p11744ajsn18f453e98759")
                    .addHeader("X-RapidAPI-Host", "http2smtp.p.rapidapi.com")
                    .build()

                val response = client.newCall(request).execute()

                if (response.isSuccessful) {
                    lifecycleScope.launch(Dispatchers.Main) {
                        val text = "Email sent with success"
                        val duration = Toast.LENGTH_SHORT

                        val toast = Toast.makeText(applicationContext, text, duration)
                        toast.show()
                    }
                } else {
                    val text = "It was not possible to send email"
                    val duration = Toast.LENGTH_SHORT

                    val toast = Toast.makeText(applicationContext, text, duration)
                    toast.show()
                }
            }


            val intent = Intent(this@EmailSendView, MainPage::class.java)
            startActivity(intent)
        }
    }


}