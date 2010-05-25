
<%@ page import="com.ulticast.domain.CallEvent" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'callEvent.label', default: 'CallEvent')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${callEventInstance}">
            <div class="errors">
                <g:renderErrors bean="${callEventInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${callEventInstance?.id}" />
                <g:hiddenField name="version" value="${callEventInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="notes"><g:message code="callEvent.notes.label" default="Notes" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: callEventInstance, field: 'notes', 'errors')}">
                                    <g:textField name="notes" value="${callEventInstance?.notes}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="lastUpdated"><g:message code="callEvent.lastUpdated.label" default="Last Updated" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: callEventInstance, field: 'lastUpdated', 'errors')}">
                                    <g:datePicker name="lastUpdated" precision="day" value="${callEventInstance?.lastUpdated}" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="callType"><g:message code="callEvent.callType.label" default="Call Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: callEventInstance, field: 'callType', 'errors')}">
                                    <g:select name="callType" from="${callEventInstance.constraints.callType.inList}" value="${fieldValue(bean: callEventInstance, field: 'callType')}" valueMessagePrefix="callEvent.callType"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="callerPlayer"><g:message code="callEvent.callerPlayer.label" default="Caller Player" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: callEventInstance, field: 'callerPlayer', 'errors')}">
                                    <g:select name="callerPlayer.id" from="${com.ulticast.domain.Player.list()}" optionKey="id" value="${callEventInstance?.callerPlayer?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="foulerPlayer"><g:message code="callEvent.foulerPlayer.label" default="Fouler Player" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: callEventInstance, field: 'foulerPlayer', 'errors')}">
                                    <g:select name="foulerPlayer.id" from="${com.ulticast.domain.Player.list()}" optionKey="id" value="${callEventInstance?.foulerPlayer?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="stallCount"><g:message code="callEvent.stallCount.label" default="Stall Count" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: callEventInstance, field: 'stallCount', 'errors')}">
                                    <g:select name="stallCount" from="${0..9}" value="${fieldValue(bean: callEventInstance, field: 'stallCount')}" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="game"><g:message code="callEvent.game.label" default="Game" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: callEventInstance, field: 'game', 'errors')}">
                                    <g:select name="game.id" from="${com.ulticast.domain.Game.list()}" optionKey="id" value="${callEventInstance?.game?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="timestamp"><g:message code="callEvent.timestamp.label" default="Timestamp" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: callEventInstance, field: 'timestamp', 'errors')}">
                                    <g:datePicker name="timestamp" precision="day" value="${callEventInstance?.timestamp}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="isContested"><g:message code="callEvent.isContested.label" default="Is Contested" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: callEventInstance, field: 'isContested', 'errors')}">
                                    <g:checkBox name="isContested" value="${callEventInstance?.isContested}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="dateCreated"><g:message code="callEvent.dateCreated.label" default="Date Created" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: callEventInstance, field: 'dateCreated', 'errors')}">
                                    <g:datePicker name="dateCreated" precision="day" value="${callEventInstance?.dateCreated}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
