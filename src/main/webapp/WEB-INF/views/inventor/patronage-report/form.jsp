<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox		code="inventor.patronage-report.form.label.sequence-number"		path="sequenceNumber" />
	<acme:input-moment		code="inventor.patronage-report.form.label.creation-moment"		path="creationMoment"   readonly="true"/>
	<acme:input-textarea code="inventor.patronage-report.form.label.memorandum"		path="memorandum" />
	<acme:input-url code="inventor.patronage-report.form.label.more-info"		path="moreInfo" />
<%-- 	<acme:input-textbox		code="inventor.patronage-report.form.label.patronage.code"		path="patronage.code" /> --%>

	<jstl:choose>
		<jstl:when test="${command == 'create'}">
		
		<acme:input-select code="inventor.patronage-report.form.label.patronages" path="patronages">
			<jstl:forEach items="${patronages}" var ="patronages">
				<acme:input-option code="${patronages}" value="${patronages}"/>
			</jstl:forEach>
		</acme:input-select>
		
			<acme:input-checkbox code="administrator.announcement.form.label.confirmation" path="confirmation" />
			<acme:submit code="inventor.patronage-report.form.button.create" action="/inventor/patronage-report/create" />
			
		</jstl:when>
	</jstl:choose>
</acme:form>