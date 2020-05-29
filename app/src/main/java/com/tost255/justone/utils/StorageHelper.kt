package com.tost255.justone.utils

import com.google.firebase.storage.FirebaseStorage
import com.tost255.justone.models.NormalCard
import com.tost255.justone.models.DbCardLangSection
import org.json.JSONArray
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectOutputStream


class StorageHelper {
    fun saveCardFromDb(lang: DbCardLangSection, myLang: DbCardLangSection){
        val numberCard = 1;
        val word: String = lang.word
        val translation: String = myLang.word
        val phrases: List<String> = castJSONArrayToStringList(lang.phrases) //todo: convert jsonarray to list
        var definition = ""
        if (lang.definition != null) definition = lang.definition
        var youtubeLink = ""
        if (lang.musicUrl != null) youtubeLink = lang.musicUrl

        val fbStorageRef = FirebaseStorage.getInstance().reference;
        val imagesRef = fbStorageRef.child("images/$word.jpg")
        val localImage = File("images/$word.jpg")
        val fImg = imagesRef.getFile(localImage)
        val imagePath = localImage.absolutePath

        val audioRef = fbStorageRef.child("en/$word.jpg")
        val localAudio = File("audio/$word.jpg")
        val fAudio = audioRef.getFile(localAudio)
        val audioPath = localAudio.absolutePath

        val card = NormalCard(numberCard, word, translation, definition, imagePath, audioPath, phrases, youtubeLink)
        try {
            val fos = FileOutputStream("normalCards/$numberCard")
            val oos = ObjectOutputStream(fos)
            oos.writeObject(card)
            oos.close()
        } catch(e: IOException){
        }

        fImg.addOnSuccessListener{
        }
        fAudio.addOnSuccessListener {
        }

    }

    private fun castJSONArrayToStringList(j: JSONArray): List<String>{
        var l: List<String> = emptyList()
        for( i in 0..j.length()-1){
            l.toMutableList().add(j.get(i) as String)
        }
        return l
        }
}