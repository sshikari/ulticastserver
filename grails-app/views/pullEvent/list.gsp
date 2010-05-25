
<%@ page import="com.ulticast.domain.PullEvent" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'pullEvent.label', default: 'PullEvent')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'pullEvent.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="notes" title="${message(code: 'pullEvent.notes.label', default: 'Notes')}" />
                        
                            <g:sortableColumn property="lastUpdated" title="${message(code: 'pullEvent.lastUpdated.label', default: 'Last Updated')}" />
                        
                            <th><g:message code="pullEvent.pullPlayer.label" default="Pull Player" /></th>
                   	    
                            <th><g:message code="pullEvent.receivePlayer.label" default="Receive Player" /></th>
                   	    
                            <th><g:message code="pullEvent.receiveTeam.label" default="Receive Team" /></th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${pullEventInstanceList}" status="i" var="pullEventInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${pullEventInstance.id}">${fieldValue(bean: pullEventInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: pullEventInstance, field: "notes")}</td>
                        
                            <td><g:formatDate date="${pullEventInstance.lastUpdated}" /></td>
                        
                            <td>${fieldValue(bean: pullEventInstance, field: "pullPlayer")}</td>
                        
                            <td>${fieldValue(bean: pullEventInstance, field: "receivePlayer")}</td>
                        
                            <td>${fieldValue(bean: pullEventInstance, field: "receiveTeam")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${pullEventInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
