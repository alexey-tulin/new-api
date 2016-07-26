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
     **/
    def index() {

        def String search = params.search as String
        def String status = params.status as String
        def String source = params.source as String
        def String type = params.type as String
        def String direction = params.direction as String
        def String dateFrom = params.dateFrom as String
        def String dateTo = params.dateTo as String
        def String sortBy = params.sortBy as String
        def String perPage = params.perPage as String
        def String page = params.page as String

        def criteria = Requests.createCriteria()
        def requestsList = criteria.list(){
//todo
        }

        def int requestDataDirections = 1
        def int responseDataDirections = 2

        def result = [
                        count: requestsList.findAll().size(),
                        list:  requestsList.collect {
                            def ContentInfo contentInfo = new ContentInfo(id: 0, size: it.fileSize, /*link: it.path + it.fileName,*/ representation: it.processingLog == null ? '' : it.processingLog.replace("<br><br>", "<br>"))
                            //def Requests req ->
                            [
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
                        }
                ]

        respond result
    }

    /**
     * Получение записи о движении данных.
     * url: /data/:id
     *
     * parameters expected in the args:
     * id (Integer) -- Идентификатор записи о движении данных
     **/

}
