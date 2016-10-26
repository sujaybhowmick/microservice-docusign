package com.optimus.docusign.controllers

import com.optimus.docusign.models.Auth
import com.optimus.docusign.models.UserToken
import com.optimus.docusign.services.OAuthTokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Created by sbhowmick on 10/25/16.
 */

@RestController
class DocuSignController: AbstractController() {

    @Autowired
    lateinit var oAuthTokenService: OAuthTokenService

    @RequestMapping(value = "/auth", method = arrayOf(RequestMethod.POST),
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun getToken(@RequestBody auth: Auth): ResponseEntity<UserToken> = ResponseEntity.ok(oAuthTokenService.getToken(auth))

}