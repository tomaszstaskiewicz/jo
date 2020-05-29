package com.tost255.justone.models

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import kotlinx.serialization.Serializable
import java.io.File
import java.time.zone.ZoneOffsetTransitionRule

@Serializable
data class NormalCard (
    val number: Int,
    val word: String,
    val translation: String,
    val definition: String,
    val imagePath: String, //paths are to internal storage
    val audioPath: String,
    val phrases: List<String>?,
    val youtubeLink: String?
    ){
    fun getImage(): Bitmap? {
        return BitmapFactory.decodeFile(imagePath);    }
    fun getAudio(){
        //toDO: return MediaPlayer(?)
    }
}
