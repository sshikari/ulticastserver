
<%@ page import="com.ulticast.domain.PassEvent" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'passEvent.label', default: 'PassEvent')}" />
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
            <g:hasErrors bean="${passEventInstance}">
            <div class="errors">
                <g:renderErrors bean="${passEventInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="notes"><g:message code="passEvent.notes.label" default="Notes" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: passEventInstance, field: 'notes', 'errors')}">
                                    <g:textField name="notes" value="${passEventInstance?.notes}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lastUpdated"><g:message code="passEvent.lastUpdated.label" default="Last Updated" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: passEventInstance, field: 'lastUpdated', 'errors')}">
                                    <g:datePicker name="lastUpdated" precision="day" value="${passEventInstance?.lastUpdated}" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="throwPlayer"><g:message code="passEvent.throwPlayer.label" default="Throw Player" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: passEventInstance, field: 'throwPlayer', 'errors')}">
                                    <g:select name="throwPlayer.id" from="${com.ulticast.domain.Player.list()}" optionKey="id" value="${passEventInstance?.throwPlayer?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="receivePlayer"><g:message code="passEvent.receivePlayer.label" default="Receive Player" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: passEventInstance, field: 'receivePlayer', 'errors')}">
                                    <g:select name="receivePlayer.id" from="${com.ulticast.domain.Player.list()}" optionKey="id" value="${passEventInstance?.receivePlayer?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="stallCount"><g:message code="passEvent.stallCount.label" default="Stall Count" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: passEventInstance, field: 'stallCount', 'errors')}">
                                    <g:select name="stallCount" from="${0..9}" value="${fieldValue(bean: passEventInstance, field: 'stallCount')}" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="game"><g:message code="passEvent.game.label" default="Game" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: passEventInstance, field: 'game', 'errors')}">
                                    <g:select name="game.id" from="${com.ulticast.domain.Game.list()}" optionKey="id" value="${passEventInstance?.game?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="offenseTeam"><g:message code="passEvent.offenseTeam.label" default="Offense Team" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: passEventInstance, field: 'offenseTeam', 'errors')}">
                                    <g:select name="offenseTeam.id" from="${com.ulticast.domain.Team.list()}" optionKey="id" value="${passEventInstance?.offenseTeam?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="passCount"><g:message code="passEvent.passCount.label" default="Pass Count" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: passEventInstance, field: 'passCount', 'errors')}">
                                    <g:textField name="passCount" value="${fieldValue(bean: passEventInstance, field: 'passCount')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="timestamp"><g:message code="passEvent.timestamp.label" default="Timestamp" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: passEventInstance, field: 'timestamp', 'errors')}">
                                    <g:datePicker name="timestamp" precision="day" value="${passEventInstance?.timestamp}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dateCreated"><g:message code="passEvent.dateCreated.label" default="Date Created" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: passEventInstance, field: 'dateCreated', 'errors')}">
                                    <g:datePicker name="dateCreated" precision="day" value="${passEventInstance?.dateCreated}"  />
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
