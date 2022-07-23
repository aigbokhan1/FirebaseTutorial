package com.shadrack.firebasetutorial.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.shadrack.firebasetutorial.R
import com.shadrack.firebasetutorial.data.User
import com.shadrack.firebasetutorial.ui.viewmodel.userInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.android.synthetic.main.activity_user_info.*

@AndroidEntryPoint
class UserInfoActivity : AppCompatActivity() {

    private lateinit var databaseReference : DatabaseReference
    private val userInfoViewModel : userInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        val username = intent.getStringExtra("username")

        databaseReference = FirebaseDatabase.getInstance().getReference("usersInFirebase")

        userInfoViewModel.getUserData(username!!, databaseReference, this)
        userInfoViewModel.userInfo.observe(this){
            username_tv.text = it.username
            email_tv.text = it.email
            age_tv.text = it.age.toString()
            religion_tv.text = it.religion
        }

      /*  viewallnames_btn.setOnClickListener {

            var intent = Intent(this, AllNamesActivity::class.java)
            startActivity(intent)
        }*/
    }

}