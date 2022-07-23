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
import javax.inject.Inject

@HiltViewModel
class AllNamesViewModel @Inject constructor() : ViewModel() {
    private val _allusersdata = MutableLiveData<User>()
    val allusersdata : LiveData<User> = _allusersdata

    fun getAllUsers(userName : String, databaseReference: DatabaseReference, context: Context){

            databaseReference.child(userName).get().addOnSuccessListener {
                _allusersdata.value = User (it.child("username").value.toString(),
                    it.child("age").value.toString().toInt(),
                    it.child("religion").value.toString(),
                    it.child("email").value.toString())

            }.addOnFailureListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Error")
                builder.setMessage("Requested data doesn't exist")
                builder.setIcon(R.drawable.ic_baseline_error_24)




        }
    }
}