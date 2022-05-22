package com.intermediait.marvel.data.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object MD5Hash {
    fun hashAlgorithm(ts: String, publicKey: String) : String {
        val privateKey = "ea5a6240724d3f97cd56ec562949593e19bd6a38"
        return md5(ts+privateKey+publicKey)
    }

    private fun md5(input:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}