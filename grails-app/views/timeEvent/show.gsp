
<%@ page import="com.ulticast.domain.TimeEvent" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'timeEvent.label', default: 'TimeEvent')}" />
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
                            <td valign="top" class="name"><g:message code="timeEvent.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: timeEventInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="timeEvent.notes.label" default="Notes" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: timeEventInstance, field: "notes")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="timeEvent.lastUpdated.label" default="Last Updated" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${timeEventInstance?.lastUpdated}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="timeEvent.timeType.label" default="Time Type" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: timeEventInstance, field: "timeType")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="timeEvent.callTeam.label" default="Call Team" /></td>
                            
                            <td valign="top" class="value"><g:link controller="team" action="show" id="${timeEventInstance?.callTeam?.id}">${timeEventInstance?.callTeam?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="timeEvent.injuredPlayer.label" default="Injured Player" /></td>
                            
                            <td valign="top" class="value"><g:link controller="player" action="show" id="${timeEventInstance?.injuredPlayer?.id}">${timeEventInstance?.injuredPlayer?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="timeEvent.subInPlayer.label" default="Sub In Player" /></td>
                            
                            <td valign="top" class="value"><g:link controller="player" action="show" id="${timeEventInstance?.subInPlayer?.id}">${timeEventInstance?.subInPlayer?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="timeEvent.otherTeamSubInPlayer.label" default="Other Team Sub In Player" /></td>
                            
                            <td valign="top" class="value"><g:link controller="player" action="show" id="${timeEventInstance?.otherTeamSubInPlayer?.id}">${timeEventInstance?.otherTeamSubInPlayer?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="timeEvent.otherTeamSubOutPlayer.label" default="Other Team Sub Out Player" /></td>
                            
                            <td valign="top" class="value"><g:link controller="player" action="show" id="${timeEventInstance?.otherTeamSubOutPlayer?.id}">${timeEventInstance?.otherTeamSubOutPlayer?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="timeEvent.game.label" default="Game" /></td>
                            
                            <td valign="top" class="value"><g:link controller="game" action="show" id="${timeEventInstance?.game?.id}">${timeEventInstance?.game?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="timeEvent.timestamp.label" default="Timestamp" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${timeEventInstance?.timestamp}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="timeEvent.dateCreated.label" default="Date Created" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${timeEventInstance?.dateCreated}" /></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${timeEventInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
