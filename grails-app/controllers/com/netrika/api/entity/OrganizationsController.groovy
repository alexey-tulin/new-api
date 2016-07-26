package com.netrika.api.entity

import com.netrika.commands.DictionaryType

/**
 * Получение списка организаций
 *
 */
class OrganizationsController {

    static responseFormats = ['json']
    static allowedMethods = [index: "GET"]

    def grailsApplication;

    /**
     * Получение справочника статусов организаций.
     * url: /organizations-statuses/
     **/
    def statuses() {
        respond getDictionariesValues(DictionaryType.organizationsStatuses)
    }

    /**
     * Получение справочника типов организаций.
     * url: /organizations-types/
     **/
    def types() {
        respond getDictionariesValues(DictionaryType.organizationsTypes)
    }



    def index() {}

    def getDictionariesValues(DictionaryType type) {
        def criteria = Dictionaries.createCriteria()
        def values = criteria.list(){

            eq('dtype', type.value)

        }.collect {
            [
                    id   : it.dcode,
                    title: it.dvalue
            ]
        }
        return values
    }
}
