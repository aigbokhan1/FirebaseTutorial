package com.shadrack.firebasetutorial.ui.viewmodel

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.shadrack.firebasetutorial.R
import com.shadrack.firebasetutorial.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class userInfoViewModel @Inject constructor() : ViewModel() {

    private val _userInfo = MutableLiveData<User>()
    val userInfo : LiveData<User> = _userInfo

    fun getUserData(userName : String, databaseReference: DatabaseReference, context: Context){
      try {
          databaseReference.child(userName).get().addOnSuccessListener {
              if(it.exists()){
                  _userInfo.value = User (it.child("username").value.toString(),
                      it.child("age").value.toString().toInt(),
                      it.child("religion").value.toString(),
                      it.child("email").value.toString()
                  )
              }else{
                  val builder = AlertDialog.Builder(context)
                  builder.setTitle("Error")
                  builder.setMessage("Requested data doesn't exist")
                  builder.setIcon(R.drawable.ic_baseline_error_24)

              }

          }.addOnFailureListener {
              val builder = AlertDialog.Builder(context)
              builder.setTitle("Error")
              builder.setMessage("Requested data doesn't exist")
              builder.setIcon(R.drawable.ic_baseline_error_24)
          }

      } catch (exception : Exception){
          val builder = AlertDialog.Builder(context)
          builder.setTitle("Error")
          builder.setMessage(exception.message)
        //  either this or toast below to display message
          builder.setIcon(R.drawable.ic_baseline_error_24)
         // Toast.makeText(context, exception.message, Toast.LENGTH_SHORT).show()
      }

    }
}