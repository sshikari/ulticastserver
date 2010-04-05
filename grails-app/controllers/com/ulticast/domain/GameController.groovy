package com.ulticast.domain

class GameController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [gameInstanceList: Game.list(params), gameInstanceTotal: Game.count()]
    }

    def create = {
        def gameInstance = new Game()
        gameInstance.properties = params
        return [gameInstance: gameInstance]
    }

    def save = {
        def gameInstance = new Game(params)
        if (gameInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'game.label', default: 'Game'), gameInstance.id])}"
            redirect(action: "show", id: gameInstance.id)
        }
        else {
            render(view: "create", model: [gameInstance: gameInstance])
        }
    }

    def show = {
        def gameInstance = Game.get(params.id)
        if (!gameInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'game.label', default: 'Game'), params.id])}"
            redirect(action: "list")
        }
        else {
            [gameInstance: gameInstance]
        }
    }

    def edit = {
        def gameInstance = Game.get(params.id)
        if (!gameInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'game.label', default: 'Game'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [gameInstance: gameInstance]
        }
    }

    def update = {
        def gameInstance = Game.get(params.id)
        if (gameInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (gameInstance.version > version) {
                    
                    gameInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'game.label', default: 'Game')] as Object[], "Another user has updated this Game while you were editing")
                    render(view: "edit", model: [gameInstance: gameInstance])
                    return
                }
            }
            gameInstance.properties = params
            if (!gameInstance.hasErrors() && gameInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'game.label', default: 'Game'), gameInstance.id])}"
                redirect(action: "show", id: gameInstance.id)
            }
            else {
                render(view: "edit", model: [gameInstance: gameInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'game.label', default: 'Game'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def gameInstance = Game.get(params.id)
        if (gameInstance) {
            try {
                gameInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'game.label', default: 'Game'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'game.label', default: 'Game'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'game.label', default: 'Game'), params.id])}"
            redirect(action: "list")
        }
    }
}
