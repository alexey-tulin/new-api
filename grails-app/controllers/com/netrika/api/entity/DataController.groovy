package com.netrika.api.entity

class DataController {

    static responseFormats = ['json']
    static allowedMethods = [index: "GET"]

    def grailsApplication;

    /**
     * ��������� ����������� ����������� ������� � �������� ������.
     * url: /data-directions/
     **/
    def dataDirections() {
        respond grailsApplication.config.netrika.dataDirections;
    }

    /**
     * ��������� ����������� ���������� ������� � �������� ������.
     * url: /data-sources/
     **/
    def dataSources() {
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

}
