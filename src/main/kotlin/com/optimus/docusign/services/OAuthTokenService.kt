package com.optimus.docusign.services

import com.mashape.unirest.http.Unirest
import com.mashape.unirest.http.exceptions.UnirestException
import com.optimus.docusign.exceptions.ResourceNotFoundException
import com.optimus.docusign.exceptions.ServiceException
import com.optimus.docusign.models.Auth
import com.optimus.docusign.models.UserToken
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

/**
 * Created by sbhowmick on 10/25/16.
 */

@Service
class OAuthTokenService {

    @Value("\${docusign.baseUrl}")
    lateinit var baseUrl: String

    @Value("\${docusign.tokenEndPoint}")
    lateinit var tokenEndPoint: String

    fun getToken(authRequest: Auth): UserToken {
        val tokenUrl = baseUrl + tokenEndPoint

        val sb = StringBuilder()
        sb.append("grant_type=password")
                .append("&")
                .append("client_id=")
                .append(authRequest.integratorKey)
                .append("&")
                .append("username=")
                .append(authRequest.userEmail)
                .append("&").append("password=")
                .append(authRequest.password)
                .append("&")
                .append("scope=api")

        val headers = mapOf("Accept" to "application/json", "Content-Type" to "application/x-www-form-urlencoded")
        val result = try {
            val response = Unirest.post(tokenUrl).headers(headers).body(sb.toString()).asJson()
            val jsonObject = response.body.`object`
            val token = when(response.status) {
                200 -> UserToken(jsonObject.getString("access_token"),
                        jsonObject.getString("token_type"),
                        jsonObject.getString("scope"))
                404 -> throw ResourceNotFoundException("Resource Not found")
                500 -> throw ServiceException(response.statusText)

                else -> {
                    throw ServiceException("Unknown Error")
                }
            }
            token

        } catch(e: UnirestException) {
            throw ServiceException(e)
        }
        return result
    }
}