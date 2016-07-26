package com.netrika.commands

/**
 * Created by KatrinaBosh on 26.07.2016.
 */
class ContentInfo {

    Long id
    Long size
    String link
    String representation

    public ContentInfo writeLink(String newLink) {
        link = newLink
        return this
    }

}
