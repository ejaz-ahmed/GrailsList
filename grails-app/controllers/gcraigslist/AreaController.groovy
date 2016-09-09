package gcraigslist

import gcraigslist.area.City
import gcraigslist.area.State
import grails.gorm.multitenancy.Tenants

class AreaController {

    def index() {
        String currentTenant = Tenants.currentId()
        City city = City.findByName(currentTenant.capitalize())

        if (!city){
            if (!currentTenant == 'DEFAULT'){
                flash.notavailable = "Sorry, your city is not available yet. Thank you for your interest! This has been logged and we will keep you in mind!"
            }
            redirect(action: 'currentServiceAreas')
        } else {
            [city:city]
        }

    }

    def currentServiceAreas(){
        def states =  State.list()

        [states:states]

    }
}
