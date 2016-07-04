package com.netrika.exceptions

import com.netrika.commands.ErrorMessage
import grails.converters.JSON
import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes


class ApplicationException extends Exception {

    public static final Locale CODES_LOCALE = new Locale('codes')
    static final ctx = ServletContextHolder.servletContext.getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
    static final messageSource = ctx.messageSource

    public static final ApplicationException UNKNOWN_ERROR = new ApplicationException('unexpectedError')

    private int errorCode;
    private List<ErrorMessage> errors;

    ApplicationException(List<ErrorMessage> errors) {
        this.errors = errors
    }

    ApplicationException(String code) {
        this(messageSource.getMessage(code, null, code, null) as String,
                messageSource.getMessage(code, null, "-999", CODES_LOCALE) as Integer);
    }

    ApplicationException(String code, Throwable e) {
        this(messageSource.getMessage(code, null, code, null) as String, e,
                messageSource.getMessage(code, null, "-999", CODES_LOCALE) as Integer);
    }

    ApplicationException(int errorCode) {
        this.errorCode = errorCode
    }

    ApplicationException(String s, int errorCode) {
        super(s)
        this.errorCode = errorCode
    }

    ApplicationException(String s, Throwable throwable, int errorCode) {
        super(s, throwable)
        this.errorCode = errorCode
    }

    ApplicationException(Throwable throwable, int errorCode) {
        super(throwable)
        this.errorCode = errorCode
    }

    ApplicationException(String s, Throwable throwable, boolean b, boolean b1, int errorCode) {
        super(s, throwable, b, b1)
        this.errorCode = errorCode
    }

    int getErrorCode() {
        return errorCode
    }

    ErrorMessage toErrorMessage() {
        return new ErrorMessage(getErrorCode(), getMessage())
    }

    def toErrorMessages() {
        if (!errors) {
            return [toErrorMessage()]
        }
        return errors;
    }

    @Override
    String toString() {
        try {
            return "${getMessage()?:''} ${(toErrorMessages() as JSON)?.toString()}"
        } catch (Throwable e) {
            return super.toString();
        }
    }
}
