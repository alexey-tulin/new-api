package com.netrika.api.entity

class DataController {

    static responseFormats = ['json']
    static allowedMethods = [index: "GET"]

    def grailsApplication;

    /**
     * Получение справочника направлений записей о движении данных.
     * url: /data-directions/
     **/
    def dataDirections() {
        respond grailsApplication.config.netrika.dataDirections;
    }

    /**
     * Получение справочника источников записей о движении данных.
     * url: /data-sources/
     **/
    def dataSources() {
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

}
