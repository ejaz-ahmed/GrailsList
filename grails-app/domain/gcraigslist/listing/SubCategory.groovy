package gcraigslist.listing

class SubCategory {
    String name

    static belongsTo = [category:Category]

    static constraints = {
    }
}
