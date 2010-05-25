
<%@ page import="com.ulticast.domain.ScoreEvent" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'scoreEvent.label', default: 'ScoreEvent')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'scoreEvent.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="notes" title="${message(code: 'scoreEvent.notes.label', default: 'Notes')}" />
                        
                            <g:sortableColumn property="lastUpdated" title="${message(code: 'scoreEvent.lastUpdated.label', default: 'Last Updated')}" />
                        
                            <th><g:message code="scoreEvent.scorerPlayer.label" default="Scorer Player" /></th>
                   	    
                            <th><g:message code="scoreEvent.assisterPlayer.label" default="Assister Player" /></th>
                   	    
                            <g:sortableColumn property="distance" title="${message(code: 'scoreEvent.distance.label', default: 'Distance')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${scoreEventInstanceList}" status="i" var="scoreEventInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${scoreEventInstance.id}">${fieldValue(bean: scoreEventInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: scoreEventInstance, field: "notes")}</td>
                        
                            <td><g:formatDate date="${scoreEventInstance.lastUpdated}" /></td>
                        
                            <td>${fieldValue(bean: scoreEventInstance, field: "scorerPlayer")}</td>
                        
                            <td>${fieldValue(bean: scoreEventInstance, field: "assisterPlayer")}</td>
                        
                            <td>${fieldValue(bean: scoreEventInstance, field: "distance")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${scoreEventInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
