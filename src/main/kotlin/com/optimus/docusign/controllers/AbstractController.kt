package com.optimus.docusign.controllers

import com.optimus.docusign.exceptions.DataFormatException
import com.optimus.docusign.exceptions.ResourceNotFoundException
import com.optimus.docusign.exceptions.ServiceException
import com.optimus.docusign.models.RestErrorInfo
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.ApplicationEventPublisherAware
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by sbhowmick on 10/26/16.
 */
abstract class AbstractController: ApplicationEventPublisherAware {

    protected val log = LoggerFactory.getLogger(AbstractController::class.java)

    protected var eventPublisher: ApplicationEventPublisher? = null

    override fun setApplicationEventPublisher(applicationEventPublisher: ApplicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServiceException::class)
    @ResponseBody
    fun handleDataStoreException(ex: ServiceException, request: WebRequest, response: HttpServletResponse): RestErrorInfo {
        log.info("Converting Data Store exception to RestResponse : " + ex.message)
        return RestErrorInfo(ex, "Internal Server Error")
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException::class)
    @ResponseBody
    fun handleResourceNotFoundException(ex: ResourceNotFoundException, request: WebRequest, response: HttpServletResponse): RestErrorInfo {
        log.info("ResourceNotFoundException handler:" + ex.message)
        return RestErrorInfo(ex, "Sorry I couldn't find it.")
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataFormatException::class)
    @ResponseBody
    fun handleDataStoreException(ex: DataFormatException, request: WebRequest, response: HttpServletResponse): RestErrorInfo {
        log.info("Converting Data Store exception to RestResponse : " + ex.message)
        return RestErrorInfo(ex, "Bad Request")
    }
}