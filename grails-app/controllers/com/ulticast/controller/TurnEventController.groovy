package com.ulticast.controller

import com.ulticast.domain.*

class TurnEventController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [turnEventInstanceList: TurnEvent.list(params), turnEventInstanceTotal: TurnEvent.count()]
    }

    def create = {
        def turnEventInstance = new TurnEvent()
        turnEventInstance.properties = params
        return [turnEventInstance: turnEventInstance]
    }

    def save = {
        def turnEventInstance = new TurnEvent(params)
        if (turnEventInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'turnEvent.label', default: 'TurnEvent'), turnEventInstance.id])}"
            redirect(action: "show", id: turnEventInstance.id)
        }
        else {
            render(view: "create", model: [turnEventInstance: turnEventInstance])
        }
    }

    def show = {
        def turnEventInstance = TurnEvent.get(params.id)
        if (!turnEventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'turnEvent.label', default: 'TurnEvent'), params.id])}"
            redirect(action: "list")
        }
        else {
            [turnEventInstance: turnEventInstance]
        }
    }

    def edit = {
        def turnEventInstance = TurnEvent.get(params.id)
        if (!turnEventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'turnEvent.label', default: 'TurnEvent'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [turnEventInstance: turnEventInstance]
        }
    }

    def update = {
        def turnEventInstance = TurnEvent.get(params.id)
        if (turnEventInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (turnEventInstance.version > version) {
                    
                    turnEventInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'turnEvent.label', default: 'TurnEvent')] as Object[], "Another user has updated this TurnEvent while you were editing")
                    render(view: "edit", model: [turnEventInstance: turnEventInstance])
                    return
                }
            }
            turnEventInstance.properties = params
            if (!turnEventInstance.hasErrors() && turnEventInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'turnEvent.label', default: 'TurnEvent'), turnEventInstance.id])}"
                redirect(action: "show", id: turnEventInstance.id)
            }
            else {
                render(view: "edit", model: [turnEventInstance: turnEventInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'turnEvent.label', default: 'TurnEvent'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def turnEventInstance = TurnEvent.get(params.id)
        if (turnEventInstance) {
            try {
                turnEventInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'turnEvent.label', default: 'TurnEvent'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'turnEvent.label', default: 'TurnEvent'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'turnEvent.label', default: 'TurnEvent'), params.id])}"
            redirect(action: "list")
        }
    }
}
