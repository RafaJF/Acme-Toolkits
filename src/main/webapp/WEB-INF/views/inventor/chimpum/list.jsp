<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.chimpum.list.label.code" path="code" width="10%"/>
	<acme:list-column code="inventor.chimpum.list.label.title" path="title" width="10%"/>
	<acme:list-column code="inventor.chimpum.list.label.budget" path="newBudget" width="10%"/> 
	<acme:list-column code="inventor.chimpum.list.label.startDate" path="startDate" width="10%"/> 
	<acme:list-column code="inventor.chimpum.list.label.endDate" path="endDate" width="10%"/> 
</acme:list>