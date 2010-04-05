
<%@ page import="com.ulticast.domain.Event" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
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
                            <g:sortableColumn property="id" title="${message(code: 'event.id.label', default: 'Id')}" />
                            <th><g:message code="event.game.label" default="Game" /></th>                                                                        
                            <th><g:message code="event.team.label" default="Team" /></th>                   	    
                            <th><g:message code="event.type.label" default="Type" /></th>                   	    
<!--                            <th><g:message code="event.player.label" default="Player" /></th>   -->                	    
                            <g:sortableColumn property="notes" title="${message(code: 'event.notes.label', default: 'Notes')}" />
                            <g:sortableColumn property="timestamp" title="${message(code: 'event.timestamp.label', default: 'Timestamp')}" />                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${eventInstanceList}" status="i" var="eventInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${eventInstance.id}">${fieldValue(bean: eventInstance, field: "id")}</g:link></td>

                            <td>${fieldValue(bean: eventInstance, field: "game.homeTeam.teamName")}
								  vs 
                            	  ${fieldValue(bean: eventInstance, field: "game.awayTeam.teamName")}
                            	 </td>
                            <td>${fieldValue(bean: eventInstance, field: "team.teamName")}</td>
                            <td>${fieldValue(bean: eventInstance, field: "class")}</td>

                        
                            <td>${fieldValue(bean: eventInstance, field: "notes")}</td>
                        
                            <td>${fieldValue(bean: eventInstance, field: "timestamp")}</td>
                                                
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${eventInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
