package gcraigslist.listing

import grails.gorm.MultiTenant

class Advertisement implements MultiTenant<Advertisement> {
    String title
    String description

    String tenantId //This is the magical column that makes this work.

    static constraints = {
    }
}
