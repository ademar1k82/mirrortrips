package com.ipca.budget.travelmobile

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import org.json.JSONArray
import org.json.JSONObject

object Utils {
    fun addClickEventToButtons(layout: ConstraintLayout, callback: (textId: String) -> Unit) {
        for (i in 0 until layout.getChildCount()) {
            val view: View = layout.getChildAt(i)

            if (view is Button) {
                view.setOnClickListener{

                    val packageId = it.getResources().getResourceName(it.id);
                    val packageIdSplit = packageId.split("/");

                    callback(packageIdSplit[1])
                };
            }
        }
    }

    fun addWebPage(packageContext: Context, button: Button, url: String?) {
        button.setOnClickListener {
            val intent = Intent(packageContext, WebViewPage::class.java)
            intent.putExtra("url", url)
            startActivity(packageContext, intent, null)
        }

    }

    fun getEmailPostBody(email: String, subject: String, emailBody: String): String {
        val emailJsonObject = JSONObject()

        emailJsonObject
            .put("body", emailBody)
            .put("isHtml", false)
            .put("plaintextAlternativeBody", null)
            .put("subject", subject)
            .put("from", JSONObject()
                .put("address", email)
                .put("name", "Mirror trips")
            )
            .put( "toAddresses",
                JSONArray().put("mirrortrip2@gmail.com")
            )
            .put("ccAddresses", JSONArray())
            .put("BccAddresses", JSONArray())
            .put("replyToAddresses", JSONArray())
            .put("smtp", JSONObject()
                .put("host", "mail.smtpbucket.com")
                .put("port", 8025)
                .put("requiresAuthentication", false)
                .put("userName", null)
                .put("password", null)
                .put("useSsl", false)
            )

        return emailJsonObject.toString()
    }
}