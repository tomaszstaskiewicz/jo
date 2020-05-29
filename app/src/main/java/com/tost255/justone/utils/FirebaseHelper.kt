package com.tost255.justone.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.tost255.justone.models.User
import com.tost255.justone.models.DbCardLangSection
import org.json.JSONArray
import org.json.JSONObject
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

    fun getNormalCard(poz: Int) {
        val lang = "en"
        val myLang = "pl"
        val cardRef = db.getReference("words").child(poz.toString())

        val cardListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val card = JSONObject(dataSnapshot.value as Map<*,*>)
                val langPart = (card.get(lang) as JSONObject)
                val myLangPart = (card.get(myLang) as JSONObject)
                val storage = StorageHelper()
                storage.saveCardFromDb(DbCardLangSection(
                    getKeyFromJsonObj(langPart, "word"),
                    getKeyFromJsonObj(langPart, "imageUrl"),
                    getKeyFromJsonObj(langPart, "definition"),
                    langPart.get("phrases") as JSONArray,
                    getKeyFromJsonObj(langPart, "musicUrl")),
                    DbCardLangSection(getKeyFromJsonObj(myLangPart,"word")))
            }
            override fun onCancelled(p0: DatabaseError) {
                val b="aa"
            }
        }
        cardRef.addValueEventListener(cardListener)

    }

    private fun getKeyFromJsonObj(o: JSONObject, key: String): String {
        return if (o.has(key))
            o.get(key) as String;
        else ""
    }
}
