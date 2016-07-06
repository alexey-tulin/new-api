package com.netrika.api.entity

import com.google.common.base.Strings
import com.netrika.commands.DictObject
import com.netrika.commands.DictionaryType

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
            isNotNull('codeEducation')
            if (elvl != 0) {
                eq('elvl', elvl)
            }
            if (qlvl != 0) {
                eq('qlvl', qlvl)
            }
            projections {
                distinct('codeEducation')
            }
        }.collect {
            [
                    id   : it.id, // it.codeEducation, // todo в оригинальном api всегда возвращается 0 (так как мапить можно либо id либо num). в описании - должен возвращаться int
                    title: it.codeEducation + ': ' + it.education
            ]
        }
        respond fields
    }

    /**
     * Получение списка возможных специальностей.
     * url: /education/specialization/
     *
     * parameters expected in the args:
     *    educationLevel (Integer) -- Идентификатор уровня образования
     *    educationField (String) -- Идентификатор направления образования
     **/
    def specialization() {
        def Integer educationLevel = params.educationLevel as Integer
        Integer elvl = DictionariesSpecialtyVo.getElvlByLevel(educationLevel)
        Integer qlvl = DictionariesSpecialtyVo.getQlvlByLevel(educationLevel)

        def String educationField = params.educationField as String

        def criteria = DictionariesSpecialtyVo.createCriteria()
        def specialization = criteria.list(elvl: elvl, qlvl: qlvl, educationField: educationField){

            isNotNull('codeQualifying')

            // Если соответствующий уровень образования не найден возвращаем пустой ответ.
            // ## todo ?? но в оригинальной версии работает не так
            eq('elvl', elvl)
            eq('qlvl', qlvl)

            if (!Strings.isNullOrEmpty(educationField) && !string.trim().isEmpty()) {
                eq('educationField', educationField)
            }
        }.collect {
            [
                    id   : it.id, //it.code, // todo в оригинальном api всегда возвращается 0. в описании - должен возвращаться int,
                    title: it.education+': '+it.qualifying
            ]
        }
        respond specialization
    }

    /**
     * Получение списка возможных статусов обучающихся.
     * url: /education/statuses/
     **/
    def statuses() {

        def int educationStatuseTrue = 1
        def int educationStatuseFalse = 2

        def educationStatuses = [
                new DictObject(id: educationStatuseTrue,  title: 'Обучается'),
                new DictObject(id: educationStatuseFalse, title: 'Не обучается')]

        respond educationStatuses
    }

    /**
     * Получение списка возможных форм обучения.
     * url: /education/forms/
     **/
    def forms() {

        def criteria = Dictionaries.createCriteria()
        def forms = criteria.list(){

            eq('dtype', DictionaryType.educationForms.value)
            not {'in'('dcode',['1', '2'])}

        }.collect {
            [
                    id   : it.dcode,
                    title: it.dvalue
            ]
        }

        respond forms
    }


    /**
     * Получение списка возможных уровней образования.
     * url: /education/levels/
     **/
    def levels() {

        def criteria = Dictionaries.createCriteria()
        def levels = criteria.list(){

            eq('dtype', DictionaryType.educationLevels.value)
            order('id', 'asc')

        }.collect {
            [
                    id   : it.dcode,
                    title: it.dvalue
            ]
        }

        respond levels
    }
}
