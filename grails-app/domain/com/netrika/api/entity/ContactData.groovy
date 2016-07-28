package com.netrika.api.entity

class ContactData {

    Long id
    String site         // Адрес сайта
    String email        // Адрес электронной почты
    String phone        // Контактный телефон
    Date createDat      // Дата создания записи

    static mapping = {
        table "CONTACTDATA"
        version false
    }

    static constraints = {
    }
}
