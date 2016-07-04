package com.netrika.api.entity


class Person {

    String snils
    Long addressRegistration

    static constraints = {
    }

    static mapping = {
        table "PERSON"
        version false
    }
}
