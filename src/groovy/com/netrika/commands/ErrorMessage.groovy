package com.netrika.commands
import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import org.springframework.validation.Errors

/**
 * Default error message node.
 *
 */
class ErrorMessage {

    int code
    String msg

    ErrorMessage(String code, String msg) {
        this.code = Integer.valueOf(code);
        this.msg = msg;
    }

    ErrorMessage(int code, String msg) {
        this.code = code
        this.msg = msg
    }

    public static class Builder {
        public static final Locale CODES_LOCALE = new Locale('codes')
        static final ctx = ServletContextHolder?.servletContext?.getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
        static final messageSource = ctx?.messageSource

        public static ErrorMessage build(String code) {
            new ErrorMessage(messageSource.getMessage(code, null, CODES_LOCALE) as Integer,
                    messageSource.getMessage(code, null, null))
        }

        public static List<ErrorMessage> build(Errors domainErrors) {
            domainErrors.allErrors.collect {
                new ErrorMessage(messageSource.getMessage(it, CODES_LOCALE) as Integer,
                        messageSource.getMessage(it, null))
            }
        }
    }
}
