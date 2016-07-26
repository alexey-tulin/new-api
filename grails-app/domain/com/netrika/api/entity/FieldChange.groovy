package com.netrika.api.entity

class FieldChange {

    Long id
    Long entityType     // ��� �������� (�������)
    Long entityId       // ������������� ��������
    String fieldName    // ������������ ����������� ����
    String oldValue     // ������ �������� ����
    String newValue     // ����� �������� ����
    String requestId    // ������������� ������� � ����������
    String source       // �������� ��������� ������� � ����������
    Date createDat      // ���� �������� ������

    static mapping = {
        table "FIELDCHANGE"
        version false
    }

    static constraints = {
    }
}
