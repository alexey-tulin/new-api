package com.netrika.hibernate

import org.hibernate.cfg.ImprovedNamingStrategy
import org.hibernate.internal.util.StringHelper

/**
 * Created by tulin on 04.07.16.
 */
class NetrikaNamingStrategy extends ImprovedNamingStrategy {
    String classToTableName(String className) {
        StringHelper.unqualify(className)
    }

    String propertyToColumnName(String propertyName) {
        StringHelper.unqualify(propertyName).toLowerCase()
    }
}
