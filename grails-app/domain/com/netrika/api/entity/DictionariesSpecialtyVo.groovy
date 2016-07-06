package com.netrika.api.entity

/**
 * ОК 009-2003. Общероссийский классификатор специальностей по образованию
 * (утв. Постановлением Госстандарта РФ от 30.09.2003 N 276-ст)
 * (дата введения 01.01.2004)(ред. от 31.03.2010)"
 */
class DictionariesSpecialtyVo {

    Long id
    String sg; // Код укрупненные группы специальностей и направлений подготовки.
    String td; // Код направление подготовки.
    String sp; // Код специальности.
    Integer elvl; // Код уровеня образования с учетом принятых в Российской Федерации уровней образования.
    Integer qlvl; // Код уровеня квалификации.
    String code; // Полный код.
    String codeEducation; // Код специальность.
    String education; // Название специальности.
    String codeQualifying; // Код квалификации.
    String qualifying ; // Название квалификации.

    static constraints = {
    }

    static mapping = {
        table "DICTIONARIES_SPECIALTY_VO"
        codeEducation column: "code_education"
        codeQualifying column: "code_qualifying"
        version false
    }

    // СПО
    // 5: { elvl: [5], qlvl: [1, 2] },
    // ВПО
    // 6: { elvl: [6], qlvl: [2] },
    // 7: { elvl: [6], qlvl: [5] },
    // 8: { elvl: [6], qlvl: [8] }
    // ВПО обобщенное
    // 6: { elvl: [6], qlvl: [2, 5, 8] },
    // 7: { elvl: [6], qlvl: [2, 5, 8] },
    // 8: { elvl: [6], qlvl: [2, 5, 8] }

    def static Integer getElvlByLevel(Integer educationLevel) {

        if (educationLevel ==  null)
            return 0

        def result = 0
        switch ( educationLevel ) {
            case [211, 212]:
                result = 5
                break

            case [221, 222, 223]:
                result = 6
                break

            default:
                result = 0
        }
        return result
    }

    def static Integer getQlvlByLevel(Integer educationLevel) {

        if (educationLevel ==  null)
            return 0

        def result = 0
        switch ( educationLevel ) {
            case 211:
                result = 2
                break
            case 212:
                result = 1
                break
            case 221:
                result = 2
                break
            case 222:
                result = 5
                break
            case 223:
                result = 8
                break

            default:
                result = 0
        }
        return result
    }

}
