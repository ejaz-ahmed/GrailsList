package gcraigslist.listing

import gcraigslist.area.City

class Category {
    String name

    static belongsTo = [city:City]

    static constraints = {
    }
}
