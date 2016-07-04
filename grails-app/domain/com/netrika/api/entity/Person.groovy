package com.netrika.api.entity

import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, excludes = ['class'])
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
