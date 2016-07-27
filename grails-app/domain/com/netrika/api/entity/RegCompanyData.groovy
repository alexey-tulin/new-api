package com.netrika.api.entity

class RegCompanyData {

    Long id
    String organizationRegId        // Региональный идентификатор организации
    String additionalName
    Long shifts                     // Дополнительное наименование (на момент реорганизации)
    Long citizenship                // Гражданство (для индивидуальных предпринимателей)
    String headOrganization         // Головная организация
    Address addressRegistration     // Юридический адрес
    Address addressResidence        // Фактический адрес
    String okato                    // Код ОКАТО
    String oktmo                    // Код ОКТМО
    String directorFio              // ФИО руководителя
    Long ownershipType              // Форма собственности
    Long accreditationData          // Сведения о государственной аккредитации
    Long licenseData                // Сведения о лицензии
    String industry                 // Коды видов деятельности (ОКВЭД)
    Long typeByFounder              // Вид организации по учредителям
    String qualifications           // Направления подготовки, специальности, профессии (кроме общего образования)
    Long constitution               // Организационно-правовая форма(код ОКОПФ)
    Long actualCount                // Фактическая наполняемость
    Long maxCount                   // Предельная наполняемость
    String stateBelong              // Принадлежность к государственным или муниципальным органам
    Long fedResult                  // Результат обработки федеральным сегментом
    String regCode                  // Код региона
    String name                     // Наименование
    Long organizationType
    String shortName                // Сокращенное наименование
    String inn                      // ИНН
    String kpp                      // КПП
    String ogrn                     // ОГРН
    String workStatus               // Данные о статусе организации
    Date createDat                  // Дата создания записи
    Date deleteDat                  // Дата удаления записи
    Boolean isFilial                // Является ли организация филиалом
    String ogrnIp                   // ОГРНИП
    Long foivStatus                 // Статус обработки в ФОИВ
    String foivStatusDescription    // Примечание к статусу (например, в случае ошибки)
    Date foivStatusDate             // Дата и время получения статуса от ФОИВ
    String organizationFedId        // Федеральный идентификатор организации

    static belongsTo = EducationProgramType
    static hasMany = [programTypes:EducationProgramType]

    static mapping = {
        table "REGCOMPANYDATA"
        version false
    }

    static constraints = {
    }
}
