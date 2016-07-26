package com.netrika.api.entity

import com.netrika.commands.ContentInfo

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

    /**
     * Получение списка записей о движении данных.
     * url: /data/
     *
     * parameters expected in the args:
     * search (String) -- Поисковая строка
     * status (Integer) -- Идентификатор статуса записи
     * source (Integer) -- Идентификатор источника запроса
     * type (Integer) -- Идентификатор типа запроса
     * direction (Integer) -- Идентификатор направления движения данных (исходящие/входящие)
     * dateFrom (date) -- От даты
     * dateTo (date) -- По дату
     * sortBy (String) -- Правило сортировки
     * perPage (Integer) -- Количество записей на страницу
     * page (Integer) -- Номер страницы
     *
     * url: /data/:id
     * parameters expected in the args:
     * id (Integer) -- Идентификатор записи о движении данных
     *
     **/
    def index() {

        def Long id = params.id as Long

        def Long search = params.search as Long
        def Integer status = params.status as Integer
        def Integer source = params.source as Integer
        def Integer type = params.type as Integer
        def String direction = params.direction as String
        def String dateFrom = params.dateFrom as String
        def String dateTo = params.dateTo as String
        def String sortBy = params.sortBy as String
        def String perPage = params.perPage as String
        def String page = params.page as String

        def criteria = Requests.createCriteria()
        def requestsList = criteria.list(){

            if (id) {
                eq('packageId', id)
                maxResults(1)
            }

            if (search) {
                eq('packageId', search)
            }
            if (status || status == 0) {
                eq('status', status)
            }
            if (source) {
                eq('regionCode', source);
            }

            if (type) {
                def method = grailsApplication.config.netrika.dataTypesMap.find {def item -> item.value.id == type}
                if (method) {
                    eq('action', method.key);
                }
            }

//            if ( direction && _(dicts.dataDirections).has(direction) ) {
//                where.isRequest = ( direction == dicts.requestDataDirections ) ? true : false
//            }
//todo
        }

        def result

        // один объект
        if (id) {
            if (requestsList) {
                result = toResponse(requestsList[0])
            } else {
                result = []
            }
        } else {
            // список объектов
            result = [
                    count: requestsList.findAll().size(),
                    list:  requestsList.collect { toResponse(it)  }
            ]
        }

        respond result
    }


    def LinkedHashMap<String,Object> toResponse(Requests it) {
        def ContentInfo contentInfo = new ContentInfo(id: 0, size: it.fileSize, /*link: it.path + it.fileName,*/ representation: it.processingLog == null ? '' : it.processingLog.replace("<br><br>", "<br>"))
        def int requestDataDirections = 1
        def int responseDataDirections = 2
        //def Requests req ->
        def response = [
                id       : it.packageId,
                timestamp: it.modifiedAt.toString().replace(" ", "T") + "Z",
                content  : contentInfo.writeLink(it.path + it.fileName),
                response : contentInfo.writeLink(it.path + 'response.zip'),
                source   : Regions.findByRegionId(it.regionCode).asDictObject(),
                uid      : it.id,
                type     : grailsApplication.config.netrika.dataTypesMap.get(it.action),
                status   : grailsApplication.config.netrika.dataStatuses.find {def item -> item.id == it.status},
                direction: grailsApplication.config.netrika.dataDirections.find {def item -> item.id = (it.isRequest ? requestDataDirections : responseDataDirections)}
        ]
        return response
    }
}
