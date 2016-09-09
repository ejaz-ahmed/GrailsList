package gcraigslist.area

class City {
    String name

    static belongsTo = [state:State]

    static constraints = {
    }
}
