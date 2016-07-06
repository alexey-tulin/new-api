package com.netrika.api.entity

/**
 * Справочники
 */
class Dictionaries {

    Long id
    String dtype
    String dcode
    String dvalue

    static constraints = {
    }

    static mapping = {
        table "DICTIONARIES"
        version false
    }
}
