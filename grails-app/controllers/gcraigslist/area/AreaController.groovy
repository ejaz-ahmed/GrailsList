package gcraigslist.area

import gcraigslist.listing.Advertisement
import gcraigslist.listing.Category
import gcraigslist.listing.SubCategory
import grails.gorm.multitenancy.Tenants

class AreaController {

    def index() {
        String currentTenant = Tenants.currentId()
        City city = City.findByNameOrUrlSlug(currentTenant.capitalize(),currentTenant)

        if (!city){
            if (!currentTenant == 'DEFAULT'){
                flash.notavailable = "Sorry, your city is not available yet. Thank you for your interest! This has been logged and we will keep you in mind!"
            }
            redirect(action: 'currentServiceAreas')
        } else {
            def categories = Category.list()
            [city:city, categories:categories]
        }

    }

    def currentServiceAreas(){
        def states = State.list()

        [states:states]
    }

    def listAdvertisements(){
        def advertisements = Advertisement.findAllBySubCategory(SubCategory.get(params.id))
        [advertisements:advertisements]
    }
}
