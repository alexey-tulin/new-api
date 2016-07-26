package com.netrika.api.entity

import com.netrika.commands.DictionaryType

/**
 * ѕолучение списка организаций
 *
 */
class OrganizationsController {

    static responseFormats = ['json']
    static allowedMethods = [index: "GET"]

    def grailsApplication;

    /**
     * ѕолучение справочника статусов организаций.
     * url: /organizations-statuses/
     **/
    def statuses() {

        def criteria = Dictionaries.createCriteria()
        def statuses = criteria.list(){

            eq('dtype', DictionaryType.organizationsStatuses.value)

        }.collect {
            [
                    id   : it.dcode,
                    title: it.dvalue
            ]
        }

        respond statuses
    }



    def index() {}
}
