import gcraigslist.area.City
import gcraigslist.area.State
import gcraigslist.listing.Category
import gcraigslist.listing.SubCategory

class BootStrap {

    def init = { servletContext ->
        environments{
            development{
                //Create cities and States
                def cities = ['Midland','Odessa','Stanton','Big Spring']
                State state = new State(name:"Texas",abbreviation:"TX")
                cities.each {state.addToCities(new City(name: it, urlSlug: it.toLowerCase().replaceAll("\\s","")))}
                state.save(failOnError:true)

                //create categories and subcategories. This may move to the general bootstrap section. Not environment specific
                def categories = ['Jobs':['Programming','General Labor'],
                                  'Services':['Automotive','Beauty','Creative','Cycle','Event','Farm & Garden','Financial','Household','Labor'],
                                  'Community':['Events'],
                                  'Gigs':['Computer','Creative','Crew','Domestic','Event','Labor','Talent','Writing'],
                                  'For Sale':['Antiques','Appliances','Arts+crafts','Atv/utv/sno','Auto parts','Baby+kid','Barter','Beauty+hlth','Bikes','Boats','Books','Business','Cars+trucks','Cds/dvd/vhs','Cell phones','Clothes+acc','Collectibles','Computers','Clectronics','Carm+garden','Cree','Curniture','Carage Sale','General','Heavy Equip','Household','Jewelry','Materials','Motorcycles','Music instr','Photo+video','Rvs+camp','Sporting','Tickets','Tools','Toys+games','Trailers','Video gaming','Wanted']]

                categories.each {k,v ->
                    Category category = new Category(name: k)
                    v.each {
                        category.addToSubCategories(new SubCategory(name: it))
                    }
                    category.save(failOnError: true)
                }

            }
        }
    }
    def destroy = {
    }
}
