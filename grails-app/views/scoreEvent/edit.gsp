
<%@ page import="com.ulticast.domain.ScoreEvent" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'scoreEvent.label', default: 'ScoreEvent')}" />
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
            <g:hasErrors bean="${scoreEventInstance}">
            <div class="errors">
                <g:renderErrors bean="${scoreEventInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${scoreEventInstance?.id}" />
                <g:hiddenField name="version" value="${scoreEventInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="notes"><g:message code="scoreEvent.notes.label" default="Notes" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: scoreEventInstance, field: 'notes', 'errors')}">
                                    <g:textField name="notes" value="${scoreEventInstance?.notes}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="lastUpdated"><g:message code="scoreEvent.lastUpdated.label" default="Last Updated" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: scoreEventInstance, field: 'lastUpdated', 'errors')}">
                                    <g:datePicker name="lastUpdated" precision="day" value="${scoreEventInstance?.lastUpdated}" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="scorerPlayer"><g:message code="scoreEvent.scorerPlayer.label" default="Scorer Player" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: scoreEventInstance, field: 'scorerPlayer', 'errors')}">
                                    <g:select name="scorerPlayer.id" from="${com.ulticast.domain.Player.list()}" optionKey="id" value="${scoreEventInstance?.scorerPlayer?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="assisterPlayer"><g:message code="scoreEvent.assisterPlayer.label" default="Assister Player" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: scoreEventInstance, field: 'assisterPlayer', 'errors')}">
                                    <g:select name="assisterPlayer.id" from="${com.ulticast.domain.Player.list()}" optionKey="id" value="${scoreEventInstance?.assisterPlayer?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="distance"><g:message code="scoreEvent.distance.label" default="Distance" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: scoreEventInstance, field: 'distance', 'errors')}">
                                    <g:select name="distance" from="${scoreEventInstance.constraints.distance.inList}" value="${fieldValue(bean: scoreEventInstance, field: 'distance')}" valueMessagePrefix="scoreEvent.distance" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="stallCount"><g:message code="scoreEvent.stallCount.label" default="Stall Count" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: scoreEventInstance, field: 'stallCount', 'errors')}">
                                    <g:select name="stallCount" from="${0..9}" value="${fieldValue(bean: scoreEventInstance, field: 'stallCount')}" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="game"><g:message code="scoreEvent.game.label" default="Game" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: scoreEventInstance, field: 'game', 'errors')}">
                                    <g:select name="game.id" from="${com.ulticast.domain.Game.list()}" optionKey="id" value="${scoreEventInstance?.game?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="timestamp"><g:message code="scoreEvent.timestamp.label" default="Timestamp" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: scoreEventInstance, field: 'timestamp', 'errors')}">
                                    <g:datePicker name="timestamp" precision="day" value="${scoreEventInstance?.timestamp}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="passCount"><g:message code="scoreEvent.passCount.label" default="Pass Count" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: scoreEventInstance, field: 'passCount', 'errors')}">
                                    <g:textField name="passCount" value="${fieldValue(bean: scoreEventInstance, field: 'passCount')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="dateCreated"><g:message code="scoreEvent.dateCreated.label" default="Date Created" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: scoreEventInstance, field: 'dateCreated', 'errors')}">
                                    <g:datePicker name="dateCreated" precision="day" value="${scoreEventInstance?.dateCreated}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="scorerTeam"><g:message code="scoreEvent.scorerTeam.label" default="Scorer Team" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: scoreEventInstance, field: 'scorerTeam', 'errors')}">
                                    <g:select name="scorerTeam.id" from="${com.ulticast.domain.Team.list()}" optionKey="id" value="${scoreEventInstance?.scorerTeam?.id}"  />
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
