<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<h1> Patronages </h1>
<acme:list>
	
	<acme:list-column code="patron.patronage.list.label.code" path="code" />
	<acme:list-column code="patron.patronage.list.label.status" path="status" />
	<acme:list-column code="patron.patronage.list.label.budget" path="budget"/>
</acme:list>