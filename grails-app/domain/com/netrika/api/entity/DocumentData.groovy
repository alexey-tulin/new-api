package com.netrika.api.entity

class DocumentData {

    Long id
    String series       // Серия документа (лицензии, свидетельства об аккредитации)
    String number       // Номер документа
    String reestrNumber // Реестровый номер
    Date issueDate      // Дата выдачи
    Date expiredDate    // Дата истечения срока действия


    static  mapping = {
        table "DOCUMENTDATA"
        version false
    }
    static constraints = {
    }
}
