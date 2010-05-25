
<%@ page import="com.ulticast.domain.TimeEvent" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'timeEvent.label', default: 'TimeEvent')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${timeEventInstance}">
            <div class="errors">
                <g:renderErrors bean="${timeEventInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="notes"><g:message code="timeEvent.notes.label" default="Notes" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: timeEventInstance, field: 'notes', 'errors')}">
                                    <g:textField name="notes" value="${timeEventInstance?.notes}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lastUpdated"><g:message code="timeEvent.lastUpdated.label" default="Last Updated" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: timeEventInstance, field: 'lastUpdated', 'errors')}">
                                    <g:datePicker name="lastUpdated" precision="day" value="${timeEventInstance?.lastUpdated}" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="timeType"><g:message code="timeEvent.timeType.label" default="Time Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: timeEventInstance, field: 'timeType', 'errors')}">
                                    <g:select name="timeType" from="${timeEventInstance.constraints.timeType.inList}" value="${fieldValue(bean: timeEventInstance, field: 'timeType')}" valueMessagePrefix="timeEvent.timeType"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="callTeam"><g:message code="timeEvent.callTeam.label" default="Call Team" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: timeEventInstance, field: 'callTeam', 'errors')}">
                                    <g:select name="callTeam.id" from="${com.ulticast.domain.Team.list()}" optionKey="id" value="${timeEventInstance?.callTeam?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="injuredPlayer"><g:message code="timeEvent.injuredPlayer.label" default="Injured Player" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: timeEventInstance, field: 'injuredPlayer', 'errors')}">
                                    <g:select name="injuredPlayer.id" from="${com.ulticast.domain.Player.list()}" optionKey="id" value="${timeEventInstance?.injuredPlayer?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="subInPlayer"><g:message code="timeEvent.subInPlayer.label" default="Sub In Player" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: timeEventInstance, field: 'subInPlayer', 'errors')}">
                                    <g:select name="subInPlayer.id" from="${com.ulticast.domain.Player.list()}" optionKey="id" value="${timeEventInstance?.subInPlayer?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="otherTeamSubInPlayer"><g:message code="timeEvent.otherTeamSubInPlayer.label" default="Other Team Sub In Player" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: timeEventInstance, field: 'otherTeamSubInPlayer', 'errors')}">
                                    <g:select name="otherTeamSubInPlayer.id" from="${com.ulticast.domain.Player.list()}" optionKey="id" value="${timeEventInstance?.otherTeamSubInPlayer?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="otherTeamSubOutPlayer"><g:message code="timeEvent.otherTeamSubOutPlayer.label" default="Other Team Sub Out Player" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: timeEventInstance, field: 'otherTeamSubOutPlayer', 'errors')}">
                                    <g:select name="otherTeamSubOutPlayer.id" from="${com.ulticast.domain.Player.list()}" optionKey="id" value="${timeEventInstance?.otherTeamSubOutPlayer?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="game"><g:message code="timeEvent.game.label" default="Game" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: timeEventInstance, field: 'game', 'errors')}">
                                    <g:select name="game.id" from="${com.ulticast.domain.Game.list()}" optionKey="id" value="${timeEventInstance?.game?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="timestamp"><g:message code="timeEvent.timestamp.label" default="Timestamp" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: timeEventInstance, field: 'timestamp', 'errors')}">
                                    <g:datePicker name="timestamp" precision="day" value="${timeEventInstance?.timestamp}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dateCreated"><g:message code="timeEvent.dateCreated.label" default="Date Created" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: timeEventInstance, field: 'dateCreated', 'errors')}">
                                    <g:datePicker name="dateCreated" precision="day" value="${timeEventInstance?.dateCreated}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
