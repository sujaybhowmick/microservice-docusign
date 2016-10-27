package com.optimus.docusign.models

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by sbhowmick on 10/25/16.
 */

data class UserToken(val accessToken: String, val tokenType: String, val tokenScope: String)

data class Auth(val userEmail: String, val password: String, val integratorKey: String)

data class Response(val data: Any)

data class SigningRequest(val token: String, val recipientEmail: List<Recipient>, val documentInfo: DocumentInfo)

data class DocumentInfo(val documentName: String, val position: Point)

data class Point(val x: Int, val y: Int)

data class Recipient(val email: String, val routingOrder: Int, val clientUserId: String)

class RestErrorInfo(ex: Exception, detail: String) {
   val detail: String
    val timeStamp: String
    val message: String

    init {
        this.timeStamp = df.format(Date())
        //Custom Error message information
        val errorMessage = ex.message!!.split("&".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()

        if (errorMessage != null) {
            this.message = errorMessage!![0]
            this.detail = detail
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