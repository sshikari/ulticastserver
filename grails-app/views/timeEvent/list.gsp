
<%@ page import="com.ulticast.domain.TimeEvent" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'timeEvent.label', default: 'TimeEvent')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'timeEvent.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="notes" title="${message(code: 'timeEvent.notes.label', default: 'Notes')}" />
                        
                            <g:sortableColumn property="lastUpdated" title="${message(code: 'timeEvent.lastUpdated.label', default: 'Last Updated')}" />
                        
                            <g:sortableColumn property="timeType" title="${message(code: 'timeEvent.timeType.label', default: 'Time Type')}" />
                        
                            <th><g:message code="timeEvent.callTeam.label" default="Call Team" /></th>
                   	    
                            <th><g:message code="timeEvent.injuredPlayer.label" default="Injured Player" /></th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${timeEventInstanceList}" status="i" var="timeEventInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${timeEventInstance.id}">${fieldValue(bean: timeEventInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: timeEventInstance, field: "notes")}</td>
                        
                            <td><g:formatDate date="${timeEventInstance.lastUpdated}" /></td>
                        
                            <td>${fieldValue(bean: timeEventInstance, field: "timeType")}</td>
                        
                            <td>${fieldValue(bean: timeEventInstance, field: "callTeam")}</td>
                        
                            <td>${fieldValue(bean: timeEventInstance, field: "injuredPlayer")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${timeEventInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
