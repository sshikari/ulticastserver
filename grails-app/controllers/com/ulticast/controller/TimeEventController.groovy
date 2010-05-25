package com.ulticast.controller

import com.ulticast.domain.*

class TimeEventController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [timeEventInstanceList: TimeEvent.list(params), timeEventInstanceTotal: TimeEvent.count()]
    }

    def create = {
        def timeEventInstance = new TimeEvent()
        timeEventInstance.properties = params
        return [timeEventInstance: timeEventInstance]
    }

    def save = {
        def timeEventInstance = new TimeEvent(params)
        if (timeEventInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'timeEvent.label', default: 'TimeEvent'), timeEventInstance.id])}"
            redirect(action: "show", id: timeEventInstance.id)
        }
        else {
            render(view: "create", model: [timeEventInstance: timeEventInstance])
        }
    }

    def show = {
        def timeEventInstance = TimeEvent.get(params.id)
        if (!timeEventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'timeEvent.label', default: 'TimeEvent'), params.id])}"
            redirect(action: "list")
        }
        else {
            [timeEventInstance: timeEventInstance]
        }
    }

    def edit = {
        def timeEventInstance = TimeEvent.get(params.id)
        if (!timeEventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'timeEvent.label', default: 'TimeEvent'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [timeEventInstance: timeEventInstance]
        }
    }

    def update = {
        def timeEventInstance = TimeEvent.get(params.id)
        if (timeEventInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (timeEventInstance.version > version) {
                    
                    timeEventInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'timeEvent.label', default: 'TimeEvent')] as Object[], "Another user has updated this TimeEvent while you were editing")
                    render(view: "edit", model: [timeEventInstance: timeEventInstance])
                    return
                }
            }
            timeEventInstance.properties = params
            if (!timeEventInstance.hasErrors() && timeEventInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'timeEvent.label', default: 'TimeEvent'), timeEventInstance.id])}"
                redirect(action: "show", id: timeEventInstance.id)
            }
            else {
                render(view: "edit", model: [timeEventInstance: timeEventInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'timeEvent.label', default: 'TimeEvent'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def timeEventInstance = TimeEvent.get(params.id)
        if (timeEventInstance) {
            try {
                timeEventInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'timeEvent.label', default: 'TimeEvent'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'timeEvent.label', default: 'TimeEvent'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'timeEvent.label', default: 'TimeEvent'), params.id])}"
            redirect(action: "list")
        }
    }
}
