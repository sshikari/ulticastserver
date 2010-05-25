package com.ulticast.controller

import com.ulticast.domain.*

class PassEventController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [passEventInstanceList: PassEvent.list(params), passEventInstanceTotal: PassEvent.count()]
    }

    def create = {
        def passEventInstance = new PassEvent()
        passEventInstance.properties = params
        return [passEventInstance: passEventInstance]
    }

    def save = {
        def passEventInstance = new PassEvent(params)
        if (passEventInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'passEvent.label', default: 'PassEvent'), passEventInstance.id])}"
            redirect(action: "show", id: passEventInstance.id)
        }
        else {
            render(view: "create", model: [passEventInstance: passEventInstance])
        }
    }

    def show = {
        def passEventInstance = PassEvent.get(params.id)
        if (!passEventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'passEvent.label', default: 'PassEvent'), params.id])}"
            redirect(action: "list")
        }
        else {
            [passEventInstance: passEventInstance]
        }
    }

    def edit = {
        def passEventInstance = PassEvent.get(params.id)
        if (!passEventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'passEvent.label', default: 'PassEvent'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [passEventInstance: passEventInstance]
        }
    }

    def update = {
        def passEventInstance = PassEvent.get(params.id)
        if (passEventInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (passEventInstance.version > version) {
                    
                    passEventInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'passEvent.label', default: 'PassEvent')] as Object[], "Another user has updated this PassEvent while you were editing")
                    render(view: "edit", model: [passEventInstance: passEventInstance])
                    return
                }
            }
            passEventInstance.properties = params
            if (!passEventInstance.hasErrors() && passEventInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'passEvent.label', default: 'PassEvent'), passEventInstance.id])}"
                redirect(action: "show", id: passEventInstance.id)
            }
            else {
                render(view: "edit", model: [passEventInstance: passEventInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'passEvent.label', default: 'PassEvent'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def passEventInstance = PassEvent.get(params.id)
        if (passEventInstance) {
            try {
                passEventInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'passEvent.label', default: 'PassEvent'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'passEvent.label', default: 'PassEvent'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'passEvent.label', default: 'PassEvent'), params.id])}"
            redirect(action: "list")
        }
    }
}
