package com.netrika.api.entity

import com.netrika.commands.DictObject
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

    /**
     * Получение списка организаций.
     * url: /organizations/
     *
     * parameters expected in the args:
     * search (String) -- Поисковая строка.
     * federalDistrict (Integer) -- Идентификатор федерального округа.
     * region (Integer) -- Идентификатор региона.
     * area (List) -- Массив идентификаторов районов.
     * educationLevel (Integer) -- Идентификатор уровня образования.
     * type (Integer) -- Идентификатор типа организации.
     * ownershipType (Integer) -- Идентификатор формы собственности.
     * status (List) -- Массив идентификаторов статусов организаций.
     * sortBy (String) -- Правило сортировки.
     * perPage (Integer) -- Количество записей на страницу.
     * page (Integer) -- Номер страницы.
     *
     * * Получение информации об организации.
     * url: /organizations/:id/
     *
     * parameters expected in the args:
     * id (Integer) -- Идентификатор записи об организации.
     **/
    def index() {

        // QUERY PARAMS
        def Integer id = params.id as Integer

        def String search = params.search as String

        def Integer federalDistrict = params.federalDistrict as Integer;
        def Integer region = params.region as Integer;
        def area = params.list("area")

        def Integer educationLevel = params.educationLevel as Integer;
        def Integer type = params.type as Integer;
        def Integer ownershipType = params.ownershipType as Integer;
        def status = params.list("status")
        def String sortBy = params.sortBy as String;
        def Integer perPage = params.perPage as Integer;
        def Integer page = params.page as Integer;

        def regionCode = null
        if ( federalDistrict && !region ) {
            regionCode = Regions.createCriteria().list {
                eq('foId', federalDistrict)
                projections {
                    distinct('regionId')
                }
            }

        }

        // Загружаем справочники ?????
        def dictionaries = Dictionaries.createCriteria().list {
            'in'('dtype', [DictionaryType.organizationsTypes.name() , DictionaryType.organizationsStatuses.name(), DictionaryType.typeByFounder.name(), DictionaryType.educationLevels.name(), DictionaryType.adaptationProgram.name()])
        }

        def criteria = RegCompanyData.createCriteria()
        def organizations = criteria.list(max: perPage, offset: page) {
            if (search) {
                or {
                    ilike('name', "%" + search + "%")
                    ilike('shortName', "%" + search + "%")
                    ilike('additionalName', "%" + search + "%")
                }
            }
            if (educationLevel) {
                //eq('type.educationProgramKind', educationLevel)
                'in'('programTypes.educationProgramKind', educationLevel)
            }
            if (type) {
                eq('organizationType.id', type)
            }
            if (ownershipType) {
                eq('ownershipType', ownershipType)
            }
            if (status) {
                'in'('workstatus', status)
            }
            if (regionCode) {
                eq('regCode', regionCode)
            } else if (region) {
                eq('regCode', region < 10 ? '0' + region : '' + region)
            }

            if (sortBy) {
                if (sortBy.indexOf('-') == 0) {
                    if (sortBy.length() > 1)
                        order(sortBy.substring(1), 'desc')
                } else {
                    order(sortBy, 'asc')
                }

            }

        }

        def result

        // один объект
        if (id) {
            if (organizations) {
                result = toResponse(organizations[0])
            } else {
                result = []
            }
        } else {
            // список объектов
            result = [
                    count: organizations.totalCount,
                    list:  organizations.collect { toResponse(it)  }
            ]
        }

        respond result
    }

    def LinkedHashMap<String,Object> toResponse(RegCompanyData it) {

        ContactData contactData = ContactData.findById(it.id) // todo можно замапить в саму RegCompanyData
        DictObject organizationType = getDictionariesValue(DictionaryType.organizationsTypes, it.organizationType)
        DictObject citizenship = getDictionariesValue(DictionaryType.citizenships, it.citizenship)
        DictObject organizationsStatuses = getDictionariesValue(DictionaryType.organizationsStatuses, it.workStatus)
        DictObject ownershipType = getDictionariesValue(DictionaryType.ownershipTypes, it.ownershipType)
        Regions region = Regions.findByRegionId(it.regCode)

        def response = [
                id: it.id,
                title: it.name,
                shortTitle: it.shortName,
                additionalTitle: it.additionalName,
                type: organizationType,
                citizenship: citizenship,
                isBranch: it.isFilial,
                // todo коммент с оригинала
                // FIXME:String!!! must be int
                head_id: it.headOrganization,
                registrationAddress: it.addressRegistration.address,
                actualAddress: it.addressResidence.address,
                okato: it.okato,
                oktmo: it.oktmo,
                inn: it.inn,
                kpp: it.kpp,
                ogrn: it.ogrn,
                region: region ? region.asDictObject() : new DictObject(0,""),
                director: it.directorFio,
                site: contactData ? contactData.site : "",
                email: contactData ? contactData.email : "",
                phone: contactData ? contactData.phone : "",
                status: organizationsStatuses,
                // TODO: Найти и добавить дату и время изменения статуса организации.
                statusChanged: "",
                ownershipType: ownershipType,
                // ## todo
        ]
        return response
    }

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

    def DictObject getDictionariesValue(DictionaryType type, String code) {

        if (!code)
            return new DictObject(0,"")
        def criteria = Dictionaries.createCriteria()
        def values = criteria.list(){

            eq('dtype', type.value)
            eq('dcode', code)

        }
        return values ? new DictObject(code, values[0].dvalue) : new DictObject(0,"")
    }
}
