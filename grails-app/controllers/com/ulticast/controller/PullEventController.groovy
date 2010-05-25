package com.ulticast.controller

import com.ulticast.domain.*

class PullEventController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [pullEventInstanceList: PullEvent.list(params), pullEventInstanceTotal: PullEvent.count()]
    }

    def create = {
        def pullEventInstance = new PullEvent()
        pullEventInstance.properties = params
        return [pullEventInstance: pullEventInstance]
    }

    def save = {
        def pullEventInstance = new PullEvent(params)
        if (pullEventInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'pullEvent.label', default: 'PullEvent'), pullEventInstance.id])}"
            redirect(action: "show", id: pullEventInstance.id)
        }
        else {
            render(view: "create", model: [pullEventInstance: pullEventInstance])
        }
    }

    def show = {
        def pullEventInstance = PullEvent.get(params.id)
        if (!pullEventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'pullEvent.label', default: 'PullEvent'), params.id])}"
            redirect(action: "list")
        }
        else {
            [pullEventInstance: pullEventInstance]
        }
    }

    def edit = {
        def pullEventInstance = PullEvent.get(params.id)
        if (!pullEventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'pullEvent.label', default: 'PullEvent'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [pullEventInstance: pullEventInstance]
        }
    }

    def update = {
        def pullEventInstance = PullEvent.get(params.id)
        if (pullEventInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (pullEventInstance.version > version) {
                    
                    pullEventInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'pullEvent.label', default: 'PullEvent')] as Object[], "Another user has updated this PullEvent while you were editing")
                    render(view: "edit", model: [pullEventInstance: pullEventInstance])
                    return
                }
            }
            pullEventInstance.properties = params
            if (!pullEventInstance.hasErrors() && pullEventInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'pullEvent.label', default: 'PullEvent'), pullEventInstance.id])}"
                redirect(action: "show", id: pullEventInstance.id)
            }
            else {
                render(view: "edit", model: [pullEventInstance: pullEventInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'pullEvent.label', default: 'PullEvent'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def pullEventInstance = PullEvent.get(params.id)
        if (pullEventInstance) {
            try {
                pullEventInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'pullEvent.label', default: 'PullEvent'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'pullEvent.label', default: 'PullEvent'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'pullEvent.label', default: 'PullEvent'), params.id])}"
            redirect(action: "list")
        }
    }
}
