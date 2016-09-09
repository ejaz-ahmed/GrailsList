import gcraigslist.area.City
import gcraigslist.area.State

class BootStrap {

    def init = { servletContext ->
        environments{
            development{
                def cities = ['Midland','Odessa','Stanton','Big Spring']
                State state = new State(name:"Texas",abbreviation:"TX")
                cities.each {state.addToCities(new City(name: it, urlSlug: it.toLowerCase().replaceAll("\\s","")))}

                state.save(failOnError:true)

            }
        }
    }
    def destroy = {
    }
}
