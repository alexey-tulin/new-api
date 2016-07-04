package com.netrika.api.entity

class PersonsController {

    static responseFormats = ['json']
    static allowedMethods = [index: "GET"]

    def index() {
        def persons = Person.list(max: 10).collect {
            [
                    id   : it.id,
                    snils: it.snils
            ]
        }
        respond persons
    }
}
