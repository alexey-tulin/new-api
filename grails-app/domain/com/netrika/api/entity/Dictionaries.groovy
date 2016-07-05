package com.netrika.api.entity

/**
 * Справочники
 */
class Dictionaries {

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
