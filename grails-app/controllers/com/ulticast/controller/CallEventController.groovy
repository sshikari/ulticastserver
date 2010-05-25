package com.ulticast.controller

import com.ulticast.domain.*

class CallEventController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [callEventInstanceList: CallEvent.list(params), callEventInstanceTotal: CallEvent.count()]
    }

    def create = {
        def callEventInstance = new CallEvent()
        callEventInstance.properties = params
        return [callEventInstance: callEventInstance]
    }

    def save = {
        def callEventInstance = new CallEvent(params)
        if (callEventInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'callEvent.label', default: 'CallEvent'), callEventInstance.id])}"
            redirect(action: "show", id: callEventInstance.id)
        }
        else {
            render(view: "create", model: [callEventInstance: callEventInstance])
        }
    }

    def show = {
        def callEventInstance = CallEvent.get(params.id)
        if (!callEventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'callEvent.label', default: 'CallEvent'), params.id])}"
            redirect(action: "list")
        }
        else {
            [callEventInstance: callEventInstance]
        }
    }

    def edit = {
        def callEventInstance = CallEvent.get(params.id)
        if (!callEventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'callEvent.label', default: 'CallEvent'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [callEventInstance: callEventInstance]
        }
    }

    def update = {
        def callEventInstance = CallEvent.get(params.id)
        if (callEventInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (callEventInstance.version > version) {
                    
                    callEventInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'callEvent.label', default: 'CallEvent')] as Object[], "Another user has updated this CallEvent while you were editing")
                    render(view: "edit", model: [callEventInstance: callEventInstance])
                    return
                }
            }
            callEventInstance.properties = params
            if (!callEventInstance.hasErrors() && callEventInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'callEvent.label', default: 'CallEvent'), callEventInstance.id])}"
                redirect(action: "show", id: callEventInstance.id)
            }
            else {
                render(view: "edit", model: [callEventInstance: callEventInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'callEvent.label', default: 'CallEvent'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def callEventInstance = CallEvent.get(params.id)
        if (callEventInstance) {
            try {
                callEventInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'callEvent.label', default: 'CallEvent'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'callEvent.label', default: 'CallEvent'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'callEvent.label', default: 'CallEvent'), params.id])}"
            redirect(action: "list")
        }
    }
}
