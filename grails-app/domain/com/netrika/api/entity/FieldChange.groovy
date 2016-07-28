package com.netrika.api.entity

class FieldChange {

    Long id
    Integer entityType  // Тип сущности (таблица)
    Integer entityId    // Идентификатор сущности
    String fieldName    // Наименования изменяемого поля
    String oldValue     // Старое значение поля
    String newValue     // Новое значение поля
    String requestId    // Идентификатор запроса с изменением
    String source       // Источник получения запроса с изменением
    Date createDat      // Дата создания записи

    static mapping = {
        table "FIELDCHANGE"
        version false
    }

    static constraints = {
    }
}
