<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<acme:form readonly="${readonly}">
	<acme:input-url
		code="inventor.patronage-report.form.label.sequence-number"
		path="sequenceNumber" />
	<acme:input-moment
		code="inventor.patronage-report.form.label.creation-moment"
		path="creationMoment" />
	<acme:input-url code="inventor.patronage-report.form.label.memorandum"
		path="memorandum" />
	<acme:input-url code="inventor.patronage-report.form.label.more-info"
		path="moreInfo" />
	<acme:input-textbox
		code="inventor.patronage-report.form.label.patronage.code"
		path="patronage.code" />

	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="employer.duty.form.button.create"
				action="/inventor/patronage-report/create?masterId=${masterId}" />
			<acme:input-checkbox
				code="administrator.announcement.form.label.confirmation"
				path="confirmation" />
		</jstl:when>
	</jstl:choose>
</acme:form>