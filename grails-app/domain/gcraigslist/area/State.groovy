package gcraigslist.area

class State {
    String name

    static hasMany = [cities:City]

    static constraints = {
    }
}
