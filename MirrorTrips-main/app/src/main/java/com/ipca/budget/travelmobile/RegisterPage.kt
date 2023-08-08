package com.ipca.budget.travelmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterPage : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)

        auth = FirebaseAuth.getInstance()

        val registerButton = findViewById<Button>(R.id.buttonRegisterPage)
        val editTextEmail = findViewById<EditText>(R.id.editRegisterEmail)
        val editTextPassword = findViewById<EditText>(R.id.editRegisterPassword)

        registerButton.setOnClickListener {

            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(baseContext, "user registered with success.",
                            Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@RegisterPage, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Register failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}