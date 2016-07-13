package com.netrika.api.entity

import com.netrika.commands.DictObject

class RegionsController {

    static responseFormats = ['json']
    static allowedMethods = [index: "GET"]

    /**
     * Получение списка регионов
     * url: /regions/
     *
     * parameters expected in the args:
     * federalDistrict (Integer) -- Идентификатор федерального округа
     **/
    def index() {

        def Integer federalDistrict = params.federalDistrict as Integer

        def criteria = Regions.createCriteria()
        def regions = criteria.list(federalDistrict: federalDistrict) {

            if (federalDistrict != null) {
                eq('foId', federalDistrict)
            } else {
                isNotNull('foId')
            }

            order('foId', 'asc')
            order('regionId', 'asc')

        }.collect {
            [
                    id   : it.regionId,
                    title: it.regionTitle,
                    district: [
                            id: it.foId,
                            title: it.foTitle
                    ]
            ]
        }

        respond regions
    }

    /**
     * Получение списка федеральных округов
     * url: /federal-districts/
     **/
    def federalDistricts() {
        def criteria = Regions.createCriteria()
        def districts = criteria.list() {

            isNotNull('foId')
            projections{
                groupProperty "foId"
                groupProperty "foTitle"
            }
            // ## todo в оригинале нет сортировки
            order('foTitle', 'asc')
            //sequelize.literal('DISTINCT on (fo_id) fo_id'), 'foId'
        }.collect {
            [
                    id   : it[0],
                    title: it[1]
            ]
        }

        respond districts
    }

    /**
     * Получение списка районов
     * url: /ares/
     *
     * parameters expected in the args:
     * federalDistrict (Integer) -- Идентификатор федерального округа
     * region (Integer) -- Идентификатор региона
     **/
    def areаs() {
        // ## todo комментрий с оригинала
        // NOTE: mock
        // NOTE: использовать ОКТМО
        // FIXME: Попросить Андрея Туркина сделать выгрузку томов ОКТМО в таблицу EXCEL

        def String federalDistrict = params.federalDistrict as String
        def String  region = params.region as String

        respond Collections.singletonList(new DictObject(1,""));
    }

}
