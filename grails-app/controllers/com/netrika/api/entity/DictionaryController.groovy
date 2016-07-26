package com.netrika.api.entity

import com.netrika.commands.DictionaryType

import java.nio.ByteBuffer
import java.nio.charset.Charset
import java.security.MessageDigest

class DictionaryController {

    static responseFormats = ['json']
    static allowedMethods = [index: "GET"]

    def grailsApplication;

    /**
     * Получение справочника специфик ОВЗ.
     * url: /disability-types/
     *
     *
     **/
    def disabilityTypes() {

        // TODO коммент с оригинала
        // FIXME: Справочник подменен по группе здоровья. Не забыть исправить в коде фильтрации.
        // var data = JSON.stringify(dictToList(dicts.OVZTypes), null, 2);

        def criteria = Dictionaries.createCriteria()
        def forms = criteria.list(){

            eq('dtype', DictionaryType.healthGroup.value)
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
     * Получение справочника типов гражданства
     * url: /citizenships/
     **/
    def citizenships() {
        respond grailsApplication.config.netrika.citizenships;
    }

    /**
     * Получение справочника полов
     * url: /genders/
     **/
    def genders() {
        respond grailsApplication.config.netrika.genders;
    }

    /**
     * Получение справочника возможных форм собственности
     * url: /ownership-types/
     **/
    def ownershipTypes() {
        respond grailsApplication.config.netrika.ownershipTypes;
    }

    /**
     * Получение списка параметров.
     * url: /parameters/
     **/
    def parameters() {

        def fields = FieldChange.createCriteria().list {
            projections{
                groupProperty "fieldName"
            }
        }.collect {
            def String field ->
                //сгенерировать ид
                // ## todo
                // ---------------------------------
                // из оригинала
//                if ( !strip ) { strip = 4; }
//                var crypto = require('crypto');
//                var md5 = crypto.createHash('md5');
//                var hex = md5.update(fieldName).digest('hex');
//
//                return parseInt(hex.slice(0, strip), 16);
                // ---------------------------------

                // попытка переписать
//                MessageDigest md5 = MessageDigest.getInstance("MD5")
//                def byte[] hex =field.getBytes(Charset.forName("UTF-8"))
//                hex = md5.update(hex)
//                hex = md5.digest(hex)
//                hex = Arrays.copyOfRange(hex, 0, 4);
//                def int intValue = ByteBuffer.wrap(hex).getInt();

                [
                        id   : 0,//Integer.parseInt("" + intValue, 16),
                        title: field
                ]
        }

        respond fields
    }



}
