package com.netrika.api.entity

class DataController {

    static responseFormats = ['json']
    static allowedMethods = [index: "GET"]

    def grailsApplication;

    /**
     * Получение справочника направлений записей о движении данных.
     * url: /data-directions/
     **/
    def directions() {
        respond grailsApplication.config.netrika.dataDirections;
    }

    /**
     * Получение справочника источников записей о движении данных.
     * url: /data-sources/
     **/
    def sources() {
        // todo комментраий с оригинала
        // TODO: Пока источниками данных являются регионы. Но в дальнейшем добавяться еще и ФОИВ'ы.
        // TODO: Пересмортеть способ хранения.

        def criteria = Regions.createCriteria()
        def sources = criteria.list(){

            order('id', 'asc')

        }.collect {
            [
                    id   : it.id,
                    title: it.regionTitle
            ]
        }

        respond sources;
    }

    /**
     * Получение справочника статусов записей о движении данных.
     * url: /data-statuses/
     **/
    def statuses() {
        respond grailsApplication.config.netrika.dataStatuses;
    }

    /**
     * Получение справочника типов записей о движении данных.
     * url: /data-types/
     **/
    def types() {
        respond grailsApplication.config.netrika.dataTypes;
    }

}
