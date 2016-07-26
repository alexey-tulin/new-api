package com.netrika.api.entity

class Requests {

    Date  createdAt // ���� �������� ������
    String fileName // ��� ����� � ��������
    Integer status // ������ ���������
    Date modifiedAt // ���� ���������
    String path // ���� � ����� �������
    String id // UID �������
    Integer regionCode // ��� �������
    String action // ��� ��������
    Long packageId // Id ������
    String replyTo // ��������� ��������� �������
    Long fileSize // ������  ������� � ������
    String processingLog  // ��� ���������
    boolean isRequest // ��������/��������� �����
    boolean isConsumer // ������������ ������� - ����������� ������

    static constraints = {
    }

    static mapping = {
        datasource "requests"
        table "REQUESTS"
        createdAt column: "created_at"
        fileName column: "file_name"
        modifiedAt column: "modified_at"
        regionCode column: "region_code"
        packageId column: "package_id"
        replyTo column: "reply_to"
        fileSize column: "file_size"
        processingLog column: "processing_log"
        isRequest column: "is_request"
        isConsumer column: "is_consumer"
        version false
    }
}
