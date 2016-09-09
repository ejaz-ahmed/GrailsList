package gcraigslist.listing

class SubCategory {
    String name

    static belongsTo = [category:Category]
    static hasMany = [advertisements:Advertisement]

    static constraints = {
    }

    @Override
    String toString(){
        name
    }
}
