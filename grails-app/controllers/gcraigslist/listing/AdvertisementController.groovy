package gcraigslist.listing

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AdvertisementController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Advertisement.list(params), model:[advertisementCount: Advertisement.count()]
    }

    def show(Advertisement advertisement) {
        respond advertisement
    }

    def create() {
        respond new Advertisement(params)
    }

    @Transactional
    def save(Advertisement advertisement) {
        if (advertisement == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (advertisement.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond advertisement.errors, view:'create'
            return
        }

        advertisement.save flush:true

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
            respond advertisement.errors, view:'edit'
            return
        }

        advertisement.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'advertisement.label', default: 'Advertisement'), advertisement.id])
                redirect advertisement
            }
            '*'{ respond advertisement, [status: OK] }
        }
    }

    @Transactional
    def delete(Advertisement advertisement) {

        if (advertisement == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        advertisement.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'advertisement.label', default: 'Advertisement'), advertisement.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'advertisement.label', default: 'Advertisement'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
