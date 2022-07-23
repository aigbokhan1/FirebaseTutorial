package com.shadrack.firebasetutorial.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.firebase.database.DatabaseReference
import com.shadrack.firebasetutorial.R
import com.shadrack.firebasetutorial.ui.viewmodel.AllNamesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_user_info.*
import kotlinx.android.synthetic.main.activity_user_info.username_tv
import kotlinx.android.synthetic.main.all_names_items_list.*

@AndroidEntryPoint
class AllNamesActivity : AppCompatActivity() {
     private lateinit var databaseReference : DatabaseReference
     private val AllNamesViewModel : AllNamesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_names)

        val username = intent.getStringExtra("username")

        AllNamesViewModel.getAllUsers(username!!, databaseReference, this)
        AllNamesViewModel.allusersdata.observe(this) {
            txtv_username.text = it.username
            txtv_email.text = it.email
            txtv_age.text = it.age.toString()
            txtv_religion.text = it.religion

        }
    }
}