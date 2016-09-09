package gcraigslist.area

class State {
    String name
    String abbreviation

    static hasMany = [cities:City]

    static constraints = {
        abbreviation maxSize: 2
    }
}
