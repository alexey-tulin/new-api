package com.netrika.api.entity

class EducationController {

    static responseFormats = ['json']
    static allowedMethods = [index: "GET"]

    /**
     * Получение списка возможных направлений.
     * url: /education/fields/
     *
     * parameters expected in the args:
     * educationLevel (Integer) -- Идентификатор уровня образования
     **/
    def fields() {
        def Integer educationLevel = params.educationLevel as Integer
        Integer elvl = DictionariesSpecialtyVo.getElvlByLevel(educationLevel)
        Integer qlvl = DictionariesSpecialtyVo.getQlvlByLevel(educationLevel)
        def criteria = DictionariesSpecialtyVo.createCriteria()
        def fields = criteria.list(elvl: elvl, qlvl: qlvl){
            if (elvl != 0) {
                eq('elvl', elvl)
            }
            if (qlvl != 0) {
                eq('qlvl', qlvl)
            }
        }.collect {
            [
                    id   : it.codeEducation,
                    title: it.education
            ]
        }
        respond fields
    }

    def statuses() {

    }
}
