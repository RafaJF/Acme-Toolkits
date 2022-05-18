<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column
		code="inventor.patronage-report.list.label.sequence-number"
		path="sequenceNumber" width="20%" />
	<acme:list-column
		code="inventor.patronage-report.list.label.creation-moment"
		path="creationMoment" width="15%" />
	<acme:list-column
		code="inventor.patronage-report.list.label.memorandum"
		path="memorandum" width="35%" />
		<acme:list-column
		code="inventor.patronage-report.list.label.published"
		path="published" width="25%" />

</acme:list>

<acme:button code="inventor.patronage-report.form.button.create" action="/inventor/patronage-report/create" />
