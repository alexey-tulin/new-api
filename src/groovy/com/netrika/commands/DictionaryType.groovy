package com.netrika.commands

/**
 * Словарь соответствий для справочника dictionaries
 */
enum DictionaryType {

    organizationsTypes          ("1"),
    educationProgramKind        ("2"),
    adaptationProgram           ("3"),
    educationForms              ("4"),
    educationFormsRealization   ("5"),
    typeByFounder               ("6"),
    ownershipTypes               ("7"), // todo значение 7 взято с тестовой БД
    workTime                    ("13"),
    shift                       ("14"),
    // : "15",
    annualPerformanceType       ("16"),
    organizationsStatuses       ("17"),
    documentType                ("18"),
    typeCode                    ("19"),
    healthGroup                 ("20"),
    disabilityTypes             ("21"),
    longTreatmentEducationType  ("22"),
    // : "23",
    // : "24",
    educationLevels             ("25"),
    // : "26",
    // : "27",
    tightSituation              ("28"),
    citizenships                ("30") // todo значение 30 взято с тестовой БД


    String value;

    DictionaryType(String value) {
        this.value = value
    }
}