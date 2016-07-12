package com.netrika.api.entity

/**
 * Справочник регионов и Федеральных округов РФ
 */
class Regions {

    Long id
    String regionId //Код региона
    String regionTitle //  Название региона.
    Integer foId // Идентификатор Федерального округа.
    String foTitle  // Название Федерального округа.

    static constraints = {
    }

    static mapping = {
        table "REGIONS"
        regionId column: "region_id"
        regionTitle column: "region_title"
        foId column: "fo_id"
        foTitle column: "fo_title"
        version false
    }
}
