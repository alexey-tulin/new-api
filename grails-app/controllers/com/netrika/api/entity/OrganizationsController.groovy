package com.netrika.api.entity

import com.netrika.commands.DictionaryType

/**
 * ��������� ������ �����������
 *
 */
class OrganizationsController {

    static responseFormats = ['json']
    static allowedMethods = [index: "GET"]

    def grailsApplication;

    /**
     * ��������� ����������� �������� �����������.
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
