
<%@ page import="com.ulticast.domain.CallEvent" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'callEvent.label', default: 'CallEvent')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'callEvent.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="notes" title="${message(code: 'callEvent.notes.label', default: 'Notes')}" />
                        
                            <g:sortableColumn property="lastUpdated" title="${message(code: 'callEvent.lastUpdated.label', default: 'Last Updated')}" />
                        
                            <g:sortableColumn property="callType" title="${message(code: 'callEvent.callType.label', default: 'Call Type')}" />
                        
                            <th><g:message code="callEvent.callerPlayer.label" default="Caller Player" /></th>
                   	    
                            <th><g:message code="callEvent.foulerPlayer.label" default="Fouler Player" /></th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${callEventInstanceList}" status="i" var="callEventInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${callEventInstance.id}">${fieldValue(bean: callEventInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: callEventInstance, field: "notes")}</td>
                        
                            <td><g:formatDate date="${callEventInstance.lastUpdated}" /></td>
                        
                            <td>${fieldValue(bean: callEventInstance, field: "callType")}</td>
                        
                            <td>${fieldValue(bean: callEventInstance, field: "callerPlayer")}</td>
                        
                            <td>${fieldValue(bean: callEventInstance, field: "foulerPlayer")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${callEventInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
