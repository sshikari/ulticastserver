
<%@ page import="com.ulticast.domain.TurnEvent" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'turnEvent.label', default: 'TurnEvent')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'turnEvent.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="notes" title="${message(code: 'turnEvent.notes.label', default: 'Notes')}" />
                        
                            <g:sortableColumn property="lastUpdated" title="${message(code: 'turnEvent.lastUpdated.label', default: 'Last Updated')}" />
                        
                            <g:sortableColumn property="turnType" title="${message(code: 'turnEvent.turnType.label', default: 'Turn Type')}" />
                        
                            <th><g:message code="turnEvent.hadPossessionPlayer.label" default="Had Possession Player" /></th>
                   	    
                            <th><g:message code="turnEvent.madeDefensePlayer.label" default="Made Defense Player" /></th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${turnEventInstanceList}" status="i" var="turnEventInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${turnEventInstance.id}">${fieldValue(bean: turnEventInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: turnEventInstance, field: "notes")}</td>
                        
                            <td><g:formatDate date="${turnEventInstance.lastUpdated}" /></td>
                        
                            <td>${fieldValue(bean: turnEventInstance, field: "turnType")}</td>
                        
                            <td>${fieldValue(bean: turnEventInstance, field: "hadPossessionPlayer")}</td>
                        
                            <td>${fieldValue(bean: turnEventInstance, field: "madeDefensePlayer")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${turnEventInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
