package com.ulticast.controller

import com.ulticast.domain.*
import grails.converters.*

class PlayerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [playerInstanceList: Player.list(params), playerInstanceTotal: Player.count()]
    }

    def create = {
        def playerInstance = new Player()
        playerInstance.properties = params
        return [playerInstance: playerInstance]
    }

    def save = {
        def playerInstance = new Player(params)
        if (playerInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'player.label', default: 'Player'), playerInstance.id])}"
            redirect(action: "show", id: playerInstance.id)
        }
        else {
            render(view: "create", model: [playerInstance: playerInstance])
        }
    }

    def show = {
        def playerInstance = Player.get(params.id)
        if (!playerInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'player.label', default: 'Player'), params.id])}"
            redirect(action: "list")
        }
        else {
            [playerInstance: playerInstance]
        }
    }

    def edit = {
        def playerInstance = Player.get(params.id)
        if (!playerInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'player.label', default: 'Player'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [playerInstance: playerInstance]
        }
    }

    def update = {
        def playerInstance = Player.get(params.id)
        if (playerInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (playerInstance.version > version) {
                    
                    playerInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'player.label', default: 'Player')] as Object[], "Another user has updated this Player while you were editing")
                    render(view: "edit", model: [playerInstance: playerInstance])
                    return
                }
            }
            playerInstance.properties = params
            if (!playerInstance.hasErrors() && playerInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'player.label', default: 'Player'), playerInstance.id])}"
                redirect(action: "show", id: playerInstance.id)
            }
            else {
                render(view: "edit", model: [playerInstance: playerInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'player.label', default: 'Player'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def playerInstance = Player.get(params.id)
        if (playerInstance) {
            try {
                playerInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'player.label', default: 'Player'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'player.label', default: 'Player'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'player.label', default: 'Player'), params.id])}"
            redirect(action: "list")
        }
    }
	
	def feed = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		render Player.list(params) as JSON
	}
	
	
}
