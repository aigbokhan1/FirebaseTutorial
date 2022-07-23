package com.shadrack.firebasetutorial.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.shadrack.firebasetutorial.R
import com.shadrack.firebasetutorial.data.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        save_button.setOnClickListener {

            val email = email_ed.text.toString()
            val age = age_ed.text.toString()
            val religion = religion_ed.text.toString()
            val username = username_ed.text.toString()

            if (editTextFieldNotEmpty()) {
              //  Toast.makeText(this,"Edit texts not empty", Toast.LENGTH_SHORT).show()
                databaseReference = FirebaseDatabase.getInstance().getReference("usersInFirebase")
                val user = User(username, age.toInt(), religion, email)

                databaseReference.child(username).setValue(user).addOnSuccessListener {
                    val intent = Intent(this, UserInfoActivity::class.java)
                    intent.putExtra("username", username)
                    startActivity(intent)

                }.addOnFailureListener {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("error")
                    builder.setMessage("An error occured")
                    builder.setIcon(R.drawable.ic_baseline_error_24)
                }
            }

        }

    }
    fun editTextFieldNotEmpty() : Boolean {
        when {

            username_ed.text.isEmpty() -> {
                username_ed.error = "Required field"
                return false
            }

            email_ed.text.isEmpty() -> {
                email_ed.error = "Required field"
                return false
            }

            age_ed.text.isEmpty() -> {
                age_ed.error = "Required field"
                return false
            }

            religion_ed.text.isEmpty() -> {
                religion_ed.error = "Required field"
                return false
            }

        }
        return true
    }
}