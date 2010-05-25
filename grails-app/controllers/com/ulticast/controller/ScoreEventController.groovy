package com.ulticast.controller

import com.ulticast.domain.*

class ScoreEventController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [scoreEventInstanceList: ScoreEvent.list(params), scoreEventInstanceTotal: ScoreEvent.count()]
    }

    def create = {
        def scoreEventInstance = new ScoreEvent()
        scoreEventInstance.properties = params
        return [scoreEventInstance: scoreEventInstance]
    }

    def save = {
        def scoreEventInstance = new ScoreEvent(params)
        if (scoreEventInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'scoreEvent.label', default: 'ScoreEvent'), scoreEventInstance.id])}"
            redirect(action: "show", id: scoreEventInstance.id)
        }
        else {
            render(view: "create", model: [scoreEventInstance: scoreEventInstance])
        }
    }

    def show = {
        def scoreEventInstance = ScoreEvent.get(params.id)
        if (!scoreEventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'scoreEvent.label', default: 'ScoreEvent'), params.id])}"
            redirect(action: "list")
        }
        else {
            [scoreEventInstance: scoreEventInstance]
        }
    }

    def edit = {
        def scoreEventInstance = ScoreEvent.get(params.id)
        if (!scoreEventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'scoreEvent.label', default: 'ScoreEvent'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [scoreEventInstance: scoreEventInstance]
        }
    }

    def update = {
        def scoreEventInstance = ScoreEvent.get(params.id)
        if (scoreEventInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (scoreEventInstance.version > version) {
                    
                    scoreEventInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'scoreEvent.label', default: 'ScoreEvent')] as Object[], "Another user has updated this ScoreEvent while you were editing")
                    render(view: "edit", model: [scoreEventInstance: scoreEventInstance])
                    return
                }
            }
            scoreEventInstance.properties = params
            if (!scoreEventInstance.hasErrors() && scoreEventInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'scoreEvent.label', default: 'ScoreEvent'), scoreEventInstance.id])}"
                redirect(action: "show", id: scoreEventInstance.id)
            }
            else {
                render(view: "edit", model: [scoreEventInstance: scoreEventInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'scoreEvent.label', default: 'ScoreEvent'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def scoreEventInstance = ScoreEvent.get(params.id)
        if (scoreEventInstance) {
            try {
                scoreEventInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'scoreEvent.label', default: 'ScoreEvent'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'scoreEvent.label', default: 'ScoreEvent'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'scoreEvent.label', default: 'ScoreEvent'), params.id])}"
            redirect(action: "list")
        }
    }
}
