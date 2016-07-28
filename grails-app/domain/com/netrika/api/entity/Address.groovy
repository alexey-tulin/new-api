package com.netrika.api.entity

class Address {

    Long id
    String flat                     // Помещение
    String buildingId               // Код строения ФИАС
    String address                  // Адрес
    Date createDat                  // Дата создания записи
    Date deleteDat                  // Дата удаления записи
    Long foivStatus                 // Статус обработки в ФОИВ
    String foivStatusDescription    // Примечание к статусу (например, в случае ошибки)
    Date foivStatusDate             // Дата и время получения статуса от ФОИВ

    static mapping = {
        table "ADDRESS"
        version false
    }

    static constraints = {
    }
}
