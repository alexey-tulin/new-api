// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

//grails.config.locations = [DictionaryConfig]

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        // filteringCodecForContentType.'text/html' = 'html'
    }
}


grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration
log4j.main = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
}

netrika{
    educationStatuses = [
            ['id': 1, 'title': 'Обучается'],
            ['id': 2, 'title': 'Не обучается']
    ]

    citizenships = [
            ['id': 0, 'title': 'Гражданин Российской Федерации'],
            ['id': 1, 'title': 'Иностранный гражданин'],
            ['id': 2, 'title': 'Лицо без гражданства'],
            ['id': 3, 'title': 'Гражданин Российской Федерации и иностранного государства (двойное гражданство)']
    ]

    genders = [
            ['id': 1, 'title': 'Мужской'],
            ['id': 2, 'title': 'Женский']
    ]

    /**
     * Значения и коды «Справочника форм собственности» согласно
     * классификатору форм собственности (ОКФС) ОК 027-99
     */
    ownershipTypes = [
            ['id':10, 'title': 'РОССИЙСКАЯ СОБСТВЕННОСТЬ'],
            ['id':11, 'title': 'Государственная собственность'],
            ['id':12, 'title': 'Федеральная собственность'],
            ['id':13, 'title': 'Собственность субъектов Российской Федерации'],
            ['id':14, 'title': 'Муниципальная собственность'],
            ['id':15, 'title': 'Собственность общественных и религиозных организаций (объединений)'],
            ['id':16, 'title': 'Частная собственность'],
            ['id':17, 'title': 'Смешанная российская собственность'],
            ['id':18, 'title': 'Собственность российских граждан, постоянно проживающих за границей'],
            ['id':19, 'title': 'Собственность потребительской кооперации'],
            ['id':50, 'title': 'Собственность благотворительных организаций'],
            ['id':51, 'title': 'Собственность политических общественных объединений'],
            ['id':52, 'title': 'Собственность профессиональных союзов'],
            ['id':53, 'title': 'Собственность общественных объединений'],
            ['id':54, 'title': 'Собственность религиозных объединений']
    ]

    /**
     * Напрввление движения данных
     */
    dataDirections = [
            ['id': 1, 'title': 'входящие'],
            ['id': 2, 'title': 'исходящие']
    ]

    dataStatuses = [
            ['id':0, 'title': 'Принимается'],  //"Получен пакет из СМЭВ", //"новый",
            ['id':1, 'title': 'Принят'],  //"На проверке ФЛК", //"взят на обработку 1й раз",
            ['id':2, 'title': 'Взят на проверку ФЛК'],  //"Проверен ФЛК", //"закончена обработка в первый раз",
            ['id':3, 'title': 'На проверке ФЛК'],  //"На проверке в ФОИВ", //"взят на обработку во второй раз",
            ['id':4, 'title': 'Проверен ФЛК'],  //"Проверен в ФОИВ", //"закончена обработка",
            ['id':5, 'title': 'Ошибка'],
            ['id':1002, 'title': 'Получен ответ']
    ]

    /**
     * Справочник типов записей о движении данных.
     */
    dataTypes = [
            ['id': 1, 'title': 'Найти организации по совпадениям реквизитов'],
            ['id': 2, 'title': 'Проверить состояние обработки пакета сведений об организациях'],
            ['id': 3, 'title': 'Проверить состояние обработки пакета сведений об обучении'],
            ['id': 4, 'title': 'Получить эталонный список организаций по региону'],
            ['id': 5, 'title': 'Получить эталонный список персон по региону'],
            ['id': 6, 'title': 'Получить число персон в эталонном списке по региону'],
            ['id': 7, 'title': 'Проверить состояние обработки пакета сведений о персонах'],
            ['id': 8, 'title': 'Получить полные сведения о персоне по федеральному идентификатору'],
            ['id': 9, 'title': 'Получить полные сведения о персоне по региональному идентификатору'],
            ['id': 10,'title': 'Отправить пакет сведений об организациях'],
            ['id': 11,'title': 'Отправить пакет расширенных сведений о персонах'],
            ['id': 12,'title': 'Отправить пакет сведений о персонах'],
            ['id': 13,'title': 'Запрос в ФНС'],
            ['id': 14,'title': 'Запрос в ФМС'],
            ['id': 15,'title': 'Синхронизация с ЕСНСИ'],
            ['id': 16,'title': 'Чистка данных'],
            ['id': 17,'title': 'Сверка по ФИАС'],
            ['id': 18,'title': 'Сверка ПФР']
    ]


/**
 * Справочник типов записей о движении данных.
 */
    dataTypesMap = [
        FindCompanies: [
            'id': 1,
            'title': 'Найти организации по совпадениям реквизитов'
        ],
        GetCompaniesDataState: [
            'id': 2,
            'title': 'Проверить состояние обработки пакета сведений об организациях'
        ],
        GetEduDataState: [
            'id': 3,
            'title': 'Проверить состояние обработки пакета сведений об обучении'
        ],
        GetFoivCompanies: [
            'id': 4,
            'title': 'Получить эталонный список организаций по региону'
        ],
        GetFoivPeople: [
            'id': 5,
            'title': 'Получить эталонный список персон по региону'
        ],
        GetFoivPeopleCount: [
            'id': 6,
            'title': 'Получить число персон в эталонном списке по региону'
        ],
        GetPeopleState: [
            'id': 7,
            'title': 'Проверить состояние обработки пакета сведений о персонах'
        ],
        GetPersonFullDataByFedId: [
            'id': 8,
            'title': 'Получить полные сведения о персоне по федеральному идентификатору'
        ],
        GetPersonFullDataByRegId: [
            'id': 9,
            'title': 'Получить полные сведения о персоне по региональному идентификатору'
        ],
        SendCompanies: [
            'id': 10,
            'title': 'Отправить пакет сведений об организациях'
        ],
        SendExtendedPersonData: [
            'id': 11,
            'title': 'Отправить пакет расширенных сведений о персонах'
        ],
        SendPeople: [
            'id': 12,
            'title': 'Отправить пакет сведений о персонах'
        ],
        SendFNS: [
            'id': 13,
            'title': 'Запрос в ФНС'
        ],
        SendFMS: [
            'id': 14,
            'title': 'Запрос в ФМС'
        ],
        SYNC: [
            'id': 15,
            'title': 'Синхронизация с ЕСНСИ'
        ],
        SendFLK: [
            'id': 16,
            'title': 'Чистка данных'
        ],
        SendFIAS: [
            'id': 17,
            'title': 'Сверка по ФИАС'
        ],
        SendPFR: [
            'id': 18,
            'title': 'Сверка ПФР'
        ]
    ]

    dataStatuses = [
            ['id': 0, 'title': 'Принимается'],  //"Получен пакет из СМЭВ", //"новый",
            ['id': 1, 'title': 'Принят'],  //"На проверке ФЛК", //"взят на обработку 1й раз",
            ['id': 2, 'title': 'Взят на проверку ФЛК'],  //"Проверен ФЛК", //"закончена обработка в первый раз",
            ['id': 3, 'title': 'На проверке ФЛК'],  //"На проверке в ФОИВ", //"взят на обработку во второй раз",
            ['id': 4, 'title': 'Проверен ФЛК'],  //"Проверен в ФОИВ", //"закончена обработка",
            ['id': 5, 'title': 'Ошибка'],
            ['id': 1002, 'title': 'Получен ответ']
    ]

}

