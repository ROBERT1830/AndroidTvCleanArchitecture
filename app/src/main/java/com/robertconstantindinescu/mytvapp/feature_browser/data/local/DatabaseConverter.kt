package com.robertconstantindinescu.mytvapp.feature_browser.data.local

import androidx.room.TypeConverter
import java.lang.StringBuilder


class DatabaseConverter {

    private val separator = ","
    @TypeConverter
    fun convertListToString(list: List<String>): String{

        val stringBuilder: StringBuilder = StringBuilder()
        for (item:String in list){
            stringBuilder.append(item).append(separator)
        }

        stringBuilder.setLength(stringBuilder.length - separator.length)
        return stringBuilder.toString()
    }

    @TypeConverter
    fun convertStringToList(string: String): List<String>{
       return string.split(separator)
    }
}