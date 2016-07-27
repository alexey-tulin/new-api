package com.netrika.api.entity

class Address {

    Long id
    String flat                     // Помещение
    String buildingid               // Код строения ФИАС
    String address                  // Адрес
    Date createdat                  // Дата создания записи
    Date deletedat                  // Дата удаления записи
    Long foivstatus                 // Статус обработки в ФОИВ
    String foivStatusDescription    // Примечание к статусу (например, в случае ошибки)
    Date foivStatusDate             // Дата и время получения статуса от ФОИВ

    static mapping = {
        table "ADDRESS"
        version false
    }

    static constraints = {
    }
}
