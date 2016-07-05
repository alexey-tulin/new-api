package com.netrika.api.entity

/**
 * �� 009-2003. �������������� ������������� �������������� �� �����������
 * (���. �������������� ������������ �� �� 30.09.2003 N 276-��)
 * (���� �������� 01.01.2004)(���. �� 31.03.2010)"
 */
class DictionariesSpecialtyVo {

    String sg; // ��� ����������� ������ �������������� � ����������� ����������.
    String td; // ��� ����������� ����������.
    String sp; // ��� �������������.
    Integer elvl; // ��� ������� ����������� � ������ �������� � ���������� ��������� ������� �����������.
    Integer qlvl; // ��� ������� ������������.
    String code; // ������ ���.
    String codeEducation; // ��� �������������.
    String education; // �������� �������������.
    String codeQualifying; // ��� ������������.
    String qualifying ; // �������� ������������.

    static constraints = {
    }

    static mapping = {
        table "DICTIONARIES_SPECIALTY_VO"
        codeEducation column: "code_education"
        codeQualifying column: "code_qualifying"
        version false
    }

    // ���
    // 5: { elvl: [5], qlvl: [1, 2] },
    // ���
    // 6: { elvl: [6], qlvl: [2] },
    // 7: { elvl: [6], qlvl: [5] },
    // 8: { elvl: [6], qlvl: [8] }
    // ��� ����������
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
