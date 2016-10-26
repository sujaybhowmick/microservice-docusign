package com.optimus.docusign.models

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by sbhowmick on 10/25/16.
 */

data class UserToken(val accessToken: String, val tokenType: String, val tokenScope: String)

data class Auth(val userEmail: String, val password: String, val integratorKey: String)

class RestErrorInfo(ex: Exception, detail: String) {
    val detail: String
    val timeStamp: String
    val message: String

    init {
        this.timeStamp = df.format(Date())
        //Custom Error message information
        val errormessage = ex.message!!.split("&".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()

        if (errormessage != null) {
            this.message = errormessage!![0]
            this.detail = errormessage!![1]
        } else {
            this.message = ex.message!!
            this.detail = detail
        }
    }

    companion object {

        private val df = SimpleDateFormat(
                "yyyy-MMM-dd HH:mm:ss z")

        init {
            df.timeZone = TimeZone.getTimeZone("GMT")
        }
    }

}