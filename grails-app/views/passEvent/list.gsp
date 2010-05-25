
<%@ page import="com.ulticast.domain.PassEvent" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'passEvent.label', default: 'PassEvent')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'passEvent.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="notes" title="${message(code: 'passEvent.notes.label', default: 'Notes')}" />
                        
                            <g:sortableColumn property="lastUpdated" title="${message(code: 'passEvent.lastUpdated.label', default: 'Last Updated')}" />
                        
                            <th><g:message code="passEvent.throwPlayer.label" default="Throw Player" /></th>
                   	    
                            <th><g:message code="passEvent.receivePlayer.label" default="Receive Player" /></th>
                   	    
                            <g:sortableColumn property="stallCount" title="${message(code: 'passEvent.stallCount.label', default: 'Stall Count')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${passEventInstanceList}" status="i" var="passEventInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${passEventInstance.id}">${fieldValue(bean: passEventInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: passEventInstance, field: "notes")}</td>
                        
                            <td><g:formatDate date="${passEventInstance.lastUpdated}" /></td>
                        
                            <td>${fieldValue(bean: passEventInstance, field: "throwPlayer")}</td>
                        
                            <td>${fieldValue(bean: passEventInstance, field: "receivePlayer")}</td>
                        
                            <td>${fieldValue(bean: passEventInstance, field: "stallCount")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${passEventInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
