package com.netrika.api.entity

class DataController {

    static responseFormats = ['json']
    static allowedMethods = [index: "GET"]

    def grailsApplication;

    /**
     * ��������� ����������� ����������� ������� � �������� ������.
     * url: /data-directions/
     **/
    def directions() {
        respond grailsApplication.config.netrika.dataDirections;
    }

    /**
     * ��������� ����������� ���������� ������� � �������� ������.
     * url: /data-sources/
     **/
    def sources() {
        // todo ����������� � ���������
        // TODO: ���� ����������� ������ �������� �������. �� � ���������� ���������� ��� � ����'�.
        // TODO: ������������ ������ ��������.

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
     * ��������� ����������� �������� ������� � �������� ������.
     * url: /data-statuses/
     **/
    def statuses() {
        respond grailsApplication.config.netrika.dataStatuses;
    }

    /**
     * ��������� ����������� ����� ������� � �������� ������.
     * url: /data-types/
     **/
    def types() {
        respond grailsApplication.config.netrika.dataTypes;
    }

}
