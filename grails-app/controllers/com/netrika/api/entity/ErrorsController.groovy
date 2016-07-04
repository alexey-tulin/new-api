package com.netrika.api.entity

import com.netrika.commands.ErrorMessage
import com.netrika.exceptions.ApplicationException

import org.codehaus.groovy.grails.web.errors.GrailsWrappedRuntimeException


import javax.servlet.http.HttpServletResponse

class ErrorsController {

    static responseFormats = ['json', 'xml']

    def applicationException() {
        ApplicationException ae = (request.exception as GrailsWrappedRuntimeException).cause as ApplicationException;
        try {
            log.error("catching application exception", ae);
            response.status = HttpServletResponse.SC_OK
            response.contentType = 'application/json'
            respond ae.toErrorMessages()
        } catch (Throwable e) {
            log.error("error", e)
            def result = [ErrorMessage.Builder.build('unexpectedError')]
            respond result
        }
    }

    def unknownError() {
        Throwable ae = (request.exception as GrailsWrappedRuntimeException).cause;
        try {
            log.error("catching uncaught exception", ae);
            response.status = HttpServletResponse.SC_OK
            def result = [ErrorMessage.Builder.build('unexpectedError')]
            respond result
        } catch (Throwable e) {
            log.error("error", e)
            def result = [ErrorMessage.Builder.build('unexpectedError')]
            respond result
        }
    }
}
