package com.netrika.api.entity

import com.netrika.commands.DictionaryType

class DictionaryController {

    static responseFormats = ['json']
    static allowedMethods = [index: "GET"]

    def grailsApplication;

    /**
     * Получение справочника специфик ОВЗ.
     * url: /disability-types/
     *
     *
     **/
    def disabilityTypes() {

        // TODO коммент с оригинала
        // FIXME: Справочник подменен по группе здоровья. Не забыть исправить в коде фильтрации.
        // var data = JSON.stringify(dictToList(dicts.OVZTypes), null, 2);

        def criteria = Dictionaries.createCriteria()
        def forms = criteria.list(){

            eq('dtype', DictionaryType.healthGroup.value)
            not {'in'('dcode',['1', '2'])}

        }.collect {
            [
                    id   : it.dcode,
                    title: it.dvalue
            ]
        }

        respond forms
    }

    /**
     * Получение справочника типов гражданства
     * url: /citizenships/
     **/
    def citizenships() {
        respond grailsApplication.config.netrika.citizenships;
    }

    /**
     * Получение справочника полов
     * url: /genders/
     **/
    def genders() {
        respond grailsApplication.config.netrika.genders;
    }

    /**
     * Получение справочника возможных форм собственности
     * url: /ownership-types/
     **/
    def ownershipTypes() {
        respond grailsApplication.config.netrika.ownershipTypes;
    }

    /**
     * Получение списка параметров.
     * url: /parameters/
     **/
    def parameters() {
        // ## todo !!!
    }



}
