package com.netrika.api.entity

class RegCompanyData {

    Long id
    String organizationRegId        // Региональный идентификатор организации
    String additionalName
    Integer shifts                  // Дополнительное наименование (на момент реорганизации)
    Integer citizenship             // Гражданство (для индивидуальных предпринимателей)
    String headOrganization         // Головная организация
    Address addressRegistration     // Юридический адрес
    Address addressResidence        // Фактический адрес
    String okato                    // Код ОКАТО
    String oktmo                    // Код ОКТМО
    String directorFio              // ФИО руководителя
    Integer ownershipType           // Форма собственности
    DocumentData accreditationData  // Сведения о государственной аккредитации
    DocumentData licenseData        // Сведения о лицензии
    String industry                 // Коды видов деятельности (ОКВЭД)
    Integer typeByFounder           // Вид организации по учредителям
    String qualifications           // Направления подготовки, специальности, профессии (кроме общего образования)
    Integer constitution            // Организационно-правовая форма(код ОКОПФ)
    Integer actualCount             // Фактическая наполняемость
    Integer maxCount                // Предельная наполняемость
    String stateBelong              // Принадлежность к государственным или муниципальным органам
    Integer fedResult               // Результат обработки федеральным сегментом
    String regCode                  // Код региона
    String name                     // Наименование
    Integer organizationType
    String shortName                // Сокращенное наименование
    String inn                      // ИНН
    String kpp                      // КПП
    String ogrn                     // ОГРН
    String workStatus               // Данные о статусе организации
    Date createDat                  // Дата создания записи
    Date deleteDat                  // Дата удаления записи
    Boolean isFilial                // Является ли организация филиалом
    String ogrnIp                   // ОГРНИП
    Integer foivStatus              // Статус обработки в ФОИВ
    String foivStatusDescription    // Примечание к статусу (например, в случае ошибки)
    Date foivStatusDate             // Дата и время получения статуса от ФОИВ
    String organizationFedId        // Федеральный идентификатор организации

    static belongsTo = EducationProgramType
    static hasMany = [programTypes:EducationProgramType]

    static mapping = {
        table "REGCOMPANYDATA"
        version false
        addressRegistration column: 'addressregistration'
        addressResidence column: 'addressResidence'
        accreditationData column: 'accreditationData'
        licenseData column: 'licenseData'
        //programTypes column:
    }

    static constraints = {
    }
}
