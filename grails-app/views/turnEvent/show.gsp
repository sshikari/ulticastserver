
<%@ page import="com.ulticast.domain.TurnEvent" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'turnEvent.label', default: 'TurnEvent')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="turnEvent.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: turnEventInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="turnEvent.notes.label" default="Notes" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: turnEventInstance, field: "notes")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="turnEvent.lastUpdated.label" default="Last Updated" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${turnEventInstance?.lastUpdated}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="turnEvent.turnType.label" default="Turn Type" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: turnEventInstance, field: "turnType")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="turnEvent.hadPossessionPlayer.label" default="Had Possession Player" /></td>
                            
                            <td valign="top" class="value"><g:link controller="player" action="show" id="${turnEventInstance?.hadPossessionPlayer?.id}">${turnEventInstance?.hadPossessionPlayer?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="turnEvent.madeDefensePlayer.label" default="Made Defense Player" /></td>
                            
                            <td valign="top" class="value"><g:link controller="player" action="show" id="${turnEventInstance?.madeDefensePlayer?.id}">${turnEventInstance?.madeDefensePlayer?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="turnEvent.stallCount.label" default="Stall Count" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: turnEventInstance, field: "stallCount")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="turnEvent.newDefenseTeam.label" default="New Defense Team" /></td>
                            
                            <td valign="top" class="value"><g:link controller="team" action="show" id="${turnEventInstance?.newDefenseTeam?.id}">${turnEventInstance?.newDefenseTeam?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="turnEvent.game.label" default="Game" /></td>
                            
                            <td valign="top" class="value"><g:link controller="game" action="show" id="${turnEventInstance?.game?.id}">${turnEventInstance?.game?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="turnEvent.timestamp.label" default="Timestamp" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${turnEventInstance?.timestamp}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="turnEvent.newOffenseTeam.label" default="New Offense Team" /></td>
                            
                            <td valign="top" class="value"><g:link controller="team" action="show" id="${turnEventInstance?.newOffenseTeam?.id}">${turnEventInstance?.newOffenseTeam?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="turnEvent.dateCreated.label" default="Date Created" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${turnEventInstance?.dateCreated}" /></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${turnEventInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
