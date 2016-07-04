package com.netrika.api.entity

import com.netrika.exceptions.ApplicationException

class PersonsController {

    static responseFormats = ['json']
    static allowedMethods = [index: "GET"]

    def index() {
        if (!params.paging) {
            throw new ApplicationException('paging.not.found')
        }
        Integer paging= params.paging as Integer
        def persons = Person.list(max: paging).collect {
            [
                    id   : it.id,
                    snils: it.snils
            ]
        }
        respond persons
    }
}
