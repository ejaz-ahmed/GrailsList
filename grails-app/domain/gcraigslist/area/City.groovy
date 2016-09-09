package gcraigslist.area

class City {
    String name
    String urlSlug

    static belongsTo = [state:State]

    static constraints = {
    }
}
