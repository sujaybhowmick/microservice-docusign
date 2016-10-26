package com.optimus.docusign.exceptions

/**
 * Created by sbhowmick on 10/26/16.
 */

class ServiceException: RuntimeException {
    constructor(e: Exception): super(e){}
    constructor(message: String): super(message){}
    constructor(message: String, e: Exception?): super(message, e)
}

class DataFormatException: RuntimeException {
    constructor(e: Exception): super(e){}
    constructor(message: String): super(message){}
    constructor(message: String, e: Exception?): super(message, e)
}

class ResourceNotFoundException: RuntimeException {
    constructor(e: Exception): super(e){}
    constructor(message: String): super(message){}
    constructor(message: String, e: Exception?): super(message, e)
}