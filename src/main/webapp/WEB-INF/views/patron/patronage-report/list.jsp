<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="patron.patronage-report.list.label.sequence-number" path="sequenceNumber"/>
	<acme:list-column code="patron.patronage-report.list.label.creation-moment" path="creationMoment"/>
	<acme:list-column code="patron.patronage-report.list.label.memorandum" path="memorandum"/>
	<acme:list-column code="patron.patronage-report.list.label.patronage-code" path="patronage.code"/>
</acme:list>