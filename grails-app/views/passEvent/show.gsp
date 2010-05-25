
<%@ page import="com.ulticast.domain.PassEvent" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'passEvent.label', default: 'PassEvent')}" />
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
                            <td valign="top" class="name"><g:message code="passEvent.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: passEventInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="passEvent.notes.label" default="Notes" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: passEventInstance, field: "notes")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="passEvent.lastUpdated.label" default="Last Updated" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${passEventInstance?.lastUpdated}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="passEvent.throwPlayer.label" default="Throw Player" /></td>
                            
                            <td valign="top" class="value"><g:link controller="player" action="show" id="${passEventInstance?.throwPlayer?.id}">${passEventInstance?.throwPlayer?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="passEvent.receivePlayer.label" default="Receive Player" /></td>
                            
                            <td valign="top" class="value"><g:link controller="player" action="show" id="${passEventInstance?.receivePlayer?.id}">${passEventInstance?.receivePlayer?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="passEvent.stallCount.label" default="Stall Count" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: passEventInstance, field: "stallCount")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="passEvent.game.label" default="Game" /></td>
                            
                            <td valign="top" class="value"><g:link controller="game" action="show" id="${passEventInstance?.game?.id}">${passEventInstance?.game?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="passEvent.offenseTeam.label" default="Offense Team" /></td>
                            
                            <td valign="top" class="value"><g:link controller="team" action="show" id="${passEventInstance?.offenseTeam?.id}">${passEventInstance?.offenseTeam?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="passEvent.passCount.label" default="Pass Count" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: passEventInstance, field: "passCount")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="passEvent.timestamp.label" default="Timestamp" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${passEventInstance?.timestamp}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="passEvent.dateCreated.label" default="Date Created" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${passEventInstance?.dateCreated}" /></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${passEventInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
