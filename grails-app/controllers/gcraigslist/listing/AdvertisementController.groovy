package gcraigslist.listing

import gcraigslist.area.City
import grails.gorm.multitenancy.Tenants

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AdvertisementController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Advertisement.list(params), model: [advertisementCount: Advertisement.count()]
    }

    def show(Advertisement advertisement) {
        respond advertisement
    }

    def create() {
        def subCategories = Category.get(params.category).subCategories
        respond new Advertisement(params), model:[subcategories:subCategories]
    }

    @Transactional
    def save(Advertisement advertisement) {
        if (advertisement == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        String currentTenant = Tenants.currentId()
        City city = City.findByNameOrUrlSlug(currentTenant.capitalize(), currentTenant)
        if (!city) {
            if (!currentTenant == 'DEFAULT') {
                flash.notavailable = "Sorry, your city is not available yet. Thank you for your interest! This has been logged and we will keep you in mind!"
            }
            redirect(action: 'currentServiceAreas')
            return
        }

        advertisement.tenantId = currentTenant
        advertisement.subCategory = SubCategory.get(1)

        advertisement.save(flush: true)


        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'advertisement.label', default: 'Advertisement'), advertisement.id])
                redirect advertisement
            }
            '*' { respond advertisement, [status: CREATED] }
        }
    }

    def edit(Advertisement advertisement) {
        respond advertisement
    }

    @Transactional
    def update(Advertisement advertisement) {
        if (advertisement == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (advertisement.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond advertisement.errors, view: 'edit'
            return
        }

        advertisement.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'advertisement.label', default: 'Advertisement'), advertisement.id])
                redirect advertisement
            }
            '*' { respond advertisement, [status: OK] }
        }
    }

    @Transactional
    def delete(Advertisement advertisement) {

        if (advertisement == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        advertisement.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'advertisement.label', default: 'Advertisement'), advertisement.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'advertisement.label', default: 'Advertisement'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
