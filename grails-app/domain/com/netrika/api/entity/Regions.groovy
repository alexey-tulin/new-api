package com.netrika.api.entity

/**
 * ���������� �������� � ����������� ������� ��
 */
class Regions {

    Long id
    String regionId //��� �������
    String regionTitle //  �������� �������.
    Integer foId // ������������� ������������ ������.
    String foTitle  // �������� ������������ ������.

    static constraints = {
    }

    static mapping = {
        table "REGIONS"
        regionId column: "region_id"
        regionTitle column: "region_title"
        foId column: "fo_id"
        foTitle column: "fo_title"
        version false
    }
}
