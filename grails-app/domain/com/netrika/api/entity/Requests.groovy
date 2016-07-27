package com.netrika.api.entity

class Requests {

    Date  createdAt         // Дата создания записи
    String fileName         // Имя файла с запросом
    Integer status          // Статус обработки
    Date modifiedAt         // Дата изменения
    String path             // Путь к файлу запроса
    String id               // UID запроса
    Integer regionCode      // Код региона
    String action           // Имя операции
    Long packageId          // Id пакета
    String replyTo          // Мнемоника источника запроса
    Long fileSize           // Размер  запроса в байтах
    String processingLog    // Лог обработки
    boolean isRequest       // входящий/исходящий пакет
    boolean isConsumer      // отправляющая система - потребитель данных

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
