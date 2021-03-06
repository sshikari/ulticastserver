
<%@ page import="com.ulticast.domain.PullEvent" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'pullEvent.label', default: 'PullEvent')}" />
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
            <g:hasErrors bean="${pullEventInstance}">
            <div class="errors">
                <g:renderErrors bean="${pullEventInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${pullEventInstance?.id}" />
                <g:hiddenField name="version" value="${pullEventInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="notes"><g:message code="pullEvent.notes.label" default="Notes" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: pullEventInstance, field: 'notes', 'errors')}">
                                    <g:textField name="notes" value="${pullEventInstance?.notes}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="lastUpdated"><g:message code="pullEvent.lastUpdated.label" default="Last Updated" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: pullEventInstance, field: 'lastUpdated', 'errors')}">
                                    <g:datePicker name="lastUpdated" precision="day" value="${pullEventInstance?.lastUpdated}" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="pullPlayer"><g:message code="pullEvent.pullPlayer.label" default="Pull Player" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: pullEventInstance, field: 'pullPlayer', 'errors')}">
                                    <g:select name="pullPlayer.id" from="${com.ulticast.domain.Player.list()}" optionKey="id" value="${pullEventInstance?.pullPlayer?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="receivePlayer"><g:message code="pullEvent.receivePlayer.label" default="Receive Player" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: pullEventInstance, field: 'receivePlayer', 'errors')}">
                                    <g:select name="receivePlayer.id" from="${com.ulticast.domain.Player.list()}" optionKey="id" value="${pullEventInstance?.receivePlayer?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="receiveTeam"><g:message code="pullEvent.receiveTeam.label" default="Receive Team" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: pullEventInstance, field: 'receiveTeam', 'errors')}">
                                    <g:select name="receiveTeam.id" from="${com.ulticast.domain.Team.list()}" optionKey="id" value="${pullEventInstance?.receiveTeam?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="game"><g:message code="pullEvent.game.label" default="Game" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: pullEventInstance, field: 'game', 'errors')}">
                                    <g:select name="game.id" from="${com.ulticast.domain.Game.list()}" optionKey="id" value="${pullEventInstance?.game?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="lineupReceiveTeam"><g:message code="pullEvent.lineupReceiveTeam.label" default="Lineup Receive Team" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: pullEventInstance, field: 'lineupReceiveTeam', 'errors')}">
                                    <g:select name="lineupReceiveTeam" from="${com.ulticast.domain.Player.list()}" multiple="yes" optionKey="id" size="5" value="${pullEventInstance?.lineupReceiveTeam}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="lineupPullTeam"><g:message code="pullEvent.lineupPullTeam.label" default="Lineup Pull Team" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: pullEventInstance, field: 'lineupPullTeam', 'errors')}">
                                    <g:select name="lineupPullTeam" from="${com.ulticast.domain.Player.list()}" multiple="yes" optionKey="id" size="5" value="${pullEventInstance?.lineupPullTeam}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="timestamp"><g:message code="pullEvent.timestamp.label" default="Timestamp" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: pullEventInstance, field: 'timestamp', 'errors')}">
                                    <g:datePicker name="timestamp" precision="day" value="${pullEventInstance?.timestamp}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="dateCreated"><g:message code="pullEvent.dateCreated.label" default="Date Created" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: pullEventInstance, field: 'dateCreated', 'errors')}">
                                    <g:datePicker name="dateCreated" precision="day" value="${pullEventInstance?.dateCreated}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="pullTeam"><g:message code="pullEvent.pullTeam.label" default="Pull Team" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: pullEventInstance, field: 'pullTeam', 'errors')}">
                                    <g:select name="pullTeam.id" from="${com.ulticast.domain.Team.list()}" optionKey="id" value="${pullEventInstance?.pullTeam?.id}"  />
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
