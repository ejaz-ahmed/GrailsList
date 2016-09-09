package gcraigslist.listing

class Category {
    String name
    static hasMany = [subCategories:SubCategory]

    static constraints = {
    }

    static mapping = {
        subCategories sort: 'name'
    }
}
