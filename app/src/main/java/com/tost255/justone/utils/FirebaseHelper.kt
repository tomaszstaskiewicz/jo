package com.tost255.justone.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.tost255.justone.models.User
import java.util.*

class FirebaseHelper{

    private val db = FirebaseDatabase.getInstance()
    private val userDb = FirebaseAuth.getInstance().currentUser
    private val userRef = db.getReference("users").child(userDb!!.uid)
    fun writeNewUserToDb(approveRegulations: Boolean) {
        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    userRef.setValue(User(userDb!!.uid,userDb.displayName, userDb.email, approveRegulations, Date(),
                        emptyList()))
                }
            }
        })
    }

    fun getUserData() {
        val userListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.value
                user.toString()
            }
            override fun onCancelled(p0: DatabaseError) {
            }
        }
        userRef.addValueEventListener(userListener)
    }
}