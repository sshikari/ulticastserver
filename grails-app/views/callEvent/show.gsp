
<%@ page import="com.ulticast.domain.CallEvent" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'callEvent.label', default: 'CallEvent')}" />
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
                            <td valign="top" class="name"><g:message code="callEvent.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: callEventInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="callEvent.notes.label" default="Notes" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: callEventInstance, field: "notes")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="callEvent.lastUpdated.label" default="Last Updated" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${callEventInstance?.lastUpdated}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="callEvent.callType.label" default="Call Type" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: callEventInstance, field: "callType")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="callEvent.callerPlayer.label" default="Caller Player" /></td>
                            
                            <td valign="top" class="value"><g:link controller="player" action="show" id="${callEventInstance?.callerPlayer?.id}">${callEventInstance?.callerPlayer?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="callEvent.foulerPlayer.label" default="Fouler Player" /></td>
                            
                            <td valign="top" class="value"><g:link controller="player" action="show" id="${callEventInstance?.foulerPlayer?.id}">${callEventInstance?.foulerPlayer?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="callEvent.stallCount.label" default="Stall Count" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: callEventInstance, field: "stallCount")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="callEvent.game.label" default="Game" /></td>
                            
                            <td valign="top" class="value"><g:link controller="game" action="show" id="${callEventInstance?.game?.id}">${callEventInstance?.game?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="callEvent.timestamp.label" default="Timestamp" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${callEventInstance?.timestamp}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="callEvent.isContested.label" default="Is Contested" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${callEventInstance?.isContested}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="callEvent.dateCreated.label" default="Date Created" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${callEventInstance?.dateCreated}" /></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${callEventInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
