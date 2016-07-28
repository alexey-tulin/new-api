package com.netrika.api.entity

class EducationProgramType {

    Long id
    Integer educationProgramKind    // Тип образовательной программы
    String educationProgramName     // Наименование образовательной программы
    Date createDat                  // Дата создания записи
    Long educationLevel             // Уровень образования


    static hasMany = [dataList:RegCompanyData]

    static mapping = {
        table "EDUCATIONPROGRAMTYPE"
        version false
    }

    static constraints = {
    }

}
