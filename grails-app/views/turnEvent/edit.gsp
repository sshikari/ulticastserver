
<%@ page import="com.ulticast.domain.TurnEvent" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'turnEvent.label', default: 'TurnEvent')}" />
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
            <g:hasErrors bean="${turnEventInstance}">
            <div class="errors">
                <g:renderErrors bean="${turnEventInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${turnEventInstance?.id}" />
                <g:hiddenField name="version" value="${turnEventInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="notes"><g:message code="turnEvent.notes.label" default="Notes" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: turnEventInstance, field: 'notes', 'errors')}">
                                    <g:textField name="notes" value="${turnEventInstance?.notes}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="lastUpdated"><g:message code="turnEvent.lastUpdated.label" default="Last Updated" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: turnEventInstance, field: 'lastUpdated', 'errors')}">
                                    <g:datePicker name="lastUpdated" precision="day" value="${turnEventInstance?.lastUpdated}" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="turnType"><g:message code="turnEvent.turnType.label" default="Turn Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: turnEventInstance, field: 'turnType', 'errors')}">
                                    <g:select name="turnType" from="${turnEventInstance.constraints.turnType.inList}" value="${fieldValue(bean: turnEventInstance, field: 'turnType')}" valueMessagePrefix="turnEvent.turnType"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="hadPossessionPlayer"><g:message code="turnEvent.hadPossessionPlayer.label" default="Had Possession Player" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: turnEventInstance, field: 'hadPossessionPlayer', 'errors')}">
                                    <g:select name="hadPossessionPlayer.id" from="${com.ulticast.domain.Player.list()}" optionKey="id" value="${turnEventInstance?.hadPossessionPlayer?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="madeDefensePlayer"><g:message code="turnEvent.madeDefensePlayer.label" default="Made Defense Player" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: turnEventInstance, field: 'madeDefensePlayer', 'errors')}">
                                    <g:select name="madeDefensePlayer.id" from="${com.ulticast.domain.Player.list()}" optionKey="id" value="${turnEventInstance?.madeDefensePlayer?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="stallCount"><g:message code="turnEvent.stallCount.label" default="Stall Count" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: turnEventInstance, field: 'stallCount', 'errors')}">
                                    <g:select name="stallCount" from="${0..9}" value="${fieldValue(bean: turnEventInstance, field: 'stallCount')}" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="newDefenseTeam"><g:message code="turnEvent.newDefenseTeam.label" default="New Defense Team" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: turnEventInstance, field: 'newDefenseTeam', 'errors')}">
                                    <g:select name="newDefenseTeam.id" from="${com.ulticast.domain.Team.list()}" optionKey="id" value="${turnEventInstance?.newDefenseTeam?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="game"><g:message code="turnEvent.game.label" default="Game" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: turnEventInstance, field: 'game', 'errors')}">
                                    <g:select name="game.id" from="${com.ulticast.domain.Game.list()}" optionKey="id" value="${turnEventInstance?.game?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="timestamp"><g:message code="turnEvent.timestamp.label" default="Timestamp" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: turnEventInstance, field: 'timestamp', 'errors')}">
                                    <g:datePicker name="timestamp" precision="day" value="${turnEventInstance?.timestamp}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="newOffenseTeam"><g:message code="turnEvent.newOffenseTeam.label" default="New Offense Team" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: turnEventInstance, field: 'newOffenseTeam', 'errors')}">
                                    <g:select name="newOffenseTeam.id" from="${com.ulticast.domain.Team.list()}" optionKey="id" value="${turnEventInstance?.newOffenseTeam?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="dateCreated"><g:message code="turnEvent.dateCreated.label" default="Date Created" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: turnEventInstance, field: 'dateCreated', 'errors')}">
                                    <g:datePicker name="dateCreated" precision="day" value="${turnEventInstance?.dateCreated}"  />
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
