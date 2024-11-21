package com.marvel.app.common.utils

import java.math.BigInteger
import java.security.MessageDigest

object Utils {

    fun generateMarvelHash(ts: String, privateKey: String, publicKey: String): String {
        val input = "$ts$privateKey$publicKey"
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    fun getFullImagePath(url:String,extention:String):String{
        if (url.isEmpty())
            return ""

        return "${url.replace("http","https")}.${extention}"
    }


}