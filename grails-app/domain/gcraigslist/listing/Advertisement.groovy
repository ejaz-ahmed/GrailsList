package gcraigslist.listing

import grails.gorm.MultiTenant

class Advertisement implements MultiTenant<Advertisement> {
    String title
    String description
    Date dateCreated
    Date lastUpdated

    String tenantId //This is the magical column that makes this work.
    static belongsTo = [subCategory:SubCategory]

    static constraints = {
    }
}
