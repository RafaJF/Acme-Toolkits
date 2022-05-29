<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	
	<acme:input-moment		code="inventor.patronage-report.form.label.creation-moment"		path="creationMoment"   readonly="true"/>
	<acme:input-textarea code="inventor.patronage-report.form.label.memorandum"		path="memorandum" />
	<acme:input-url code="inventor.patronage-report.form.label.more-info"		path="moreInfo" />


	<jstl:choose>
		
		<jstl:when test="${command == 'show'}">
			<acme:input-textbox		code="inventor.patronage-report.form.label.sequence-number"		path="sequenceNumber" />
			<acme:input-textbox		code="inventor.patronage-report.form.label.patronage.code"	path="patronage.code" />
		</jstl:when>
	
		<jstl:when test="${command == 'create'}">
		
		<acme:input-select code="inventor.patronage-report.form.label.patronages" path="patronageCode">
			<jstl:forEach items="${publishedPatronages}" var ="patronage">
				<acme:input-option code="${patronage.code}" value="${patronage.code}"/>
			</jstl:forEach>
		</acme:input-select>
		
			<acme:input-checkbox code="administrator.announcement.form.label.confirmation" path="confirmation" />
			<acme:submit code="inventor.patronage-report.form.button.create" action="/inventor/patronage-report/create" />
			
		</jstl:when>
		
	</jstl:choose>
</acme:form>